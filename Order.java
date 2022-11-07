import java.io.*;
import java.util.Map;
import java.util.Scanner;


//order is used to manipulate the orders from tables
public class Order {


    public static boolean Payment(int tableNumber) {

        double finalVal = 0;
        try {
            FileInputStream fluxIn = new FileInputStream("./src/Orders/order-table-" + tableNumber + ".txt");
            InputStreamReader isr = new InputStreamReader(fluxIn);
            BufferedReader bufferIn = new BufferedReader(isr);
            String line;
            while ((line = bufferIn.readLine()) != null) {
                if(!line.isBlank()){
                String[] columns = line.split("=");
                double value = Double.parseDouble(columns[1].trim());
                finalVal = finalVal + value;
                System.out.println(columns[0].replace("_", " ") + " - " + columns[1] + " RON");
                }
            }
        } catch (IOException e) {
            System.out.println("Sorry we do not have that type of menu.");

        }
        System.out.println("Collected payment: " + String.format("%.2f",finalVal));
        if(tableNumber == 0){
            System.out.println("To go orders must be paid upfront!");
        }
        System.out.println("Process payment? (yes/no)");
        Scanner scan = new Scanner(System.in);
        String agree = scan.nextLine().toLowerCase();

        while (!agree.equals("yes")){
            System.out.println("Order cannot be processed!");

            agree = scan.nextLine().toLowerCase();
        }
        System.out.println("Clear order table " + tableNumber);
        Clear(tableNumber);
        return true;

    }

    public static void ToAdd(int tableNumber, String arg) {
        try {
            FileWriter myWriter = new FileWriter("./src/Orders/order-table-" + tableNumber + ".txt", true);
            arg = arg.replace(" ", "_");
            String fullLine = arg + " = "+Menu.menu.get(arg);
            myWriter.write("\n" + fullLine);
            myWriter.close();
        } catch (IOException e){
            System.out.println();
        }


    }

    public static void Clear(int tableNumber){
        try{
            FileWriter myWriter = new FileWriter("./src/Orders/order-table-" + tableNumber + ".txt");
            myWriter.close();
        }catch (IOException e){
            System.out.println();
        }

    }

    public void ToCreate(int tableNumber) {
        try {
            File myOrder = new File("./src/Orders/order-table-" + tableNumber + ".txt");
            if (myOrder.createNewFile()) {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean ToDelete(int tableNumber, String arg) throws IOException {
        File inputFile = new File("./src/Orders/order-table-" + tableNumber + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        File tempFile = new File("./src/Orders/temp.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String toRemove = arg;
        String currentLine;

        while((currentLine = reader.readLine()) != null){
            String[] splitedLine = currentLine.split("=");
            String orderName = splitedLine[0].trim();
            System.out.println(orderName);
            if(orderName.equals(toRemove)) {
                continue;
            }
            writer.write(currentLine + "\n");

        }
        writer.close();
        reader.close();
        return tempFile.renameTo(inputFile);

    }



}
