import java.io.*;
import java.util.ArrayList;
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

    // meth to add to orders
    public static void ToAdd(int tableNumber, String arg) {

        ArrayList<String> orderToAdd = ToOpen(tableNumber);
        int multiplier = 1;
        String orderName;
        // split multiple order by #
        if (arg.contains("#")) {
            String[] orderPart = arg.split("#");
            orderName = orderPart[0].trim().toLowerCase();
            System.out.println(orderName);
            if (Functions.isInteger(orderPart[1].trim())) {
                multiplier = Integer.parseInt(orderPart[1].trim());
            }
        } else orderName = arg;
        String newFullOrder = "";
        String fullOrder;
        System.out.println(orderName);
        fullOrder = orderName + " ----- " + multiplier + " x " + Menu.menu.get(orderName) + " => " + multiplier * Menu.menu.get(orderName);
        for (String d : orderToAdd) {
            if (d.contains(orderName)) {
                String[] newLine = d.split( "-----" );
                String[] oldMultiplierArr = newLine[1].split("x");
                int oldMultiplier = Integer.parseInt(oldMultiplierArr[0].trim());
                newFullOrder = orderName + " ----- " + (multiplier + oldMultiplier) + " x " + Menu.menu.get(orderName) + " => " + (oldMultiplier+multiplier) * Menu.menu.get(orderName);
                int index = orderToAdd.indexOf(d);
                System.out.println(index);
                orderToAdd.set(index,newFullOrder);
            }
            }
        if(newFullOrder.equals("")){
            orderToAdd.add(fullOrder);
        }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("./src/Orders/order-table-" + tableNumber + ".txt"));
                for (String s : orderToAdd) {
                    writer.write(s + "\n");
                }
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    // method to open orders (either to display or to modify)
    public static ArrayList<String> ToOpen(int tableNumber){
        String line;
        ArrayList<String> order = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("./src/Orders/order-table-" + tableNumber + ".txt"));
            while((line = reader.readLine()) != null){
                order.add(line);
            }
            reader.close();
        }catch (Exception ex){
                ex.printStackTrace();
        }
        return order;

    }


    ///Clear might be deleted
    public static void Clear(int tableNumber){
        try{
            FileWriter myWriter = new FileWriter("./src/Orders/order-table-" + tableNumber + ".txt");
            myWriter.close();
        }catch (IOException e){
            System.out.println();
        }

    }




    public static void ToEdit(int tableNumber, String what, int right, int wrong){
        ArrayList<String> orderToAdd = ToOpen(tableNumber);
        String longName = what + " ----- " + wrong + " x "  + Menu.menu.get(what) + " => " + wrong*Menu.menu.get(what);
        System.out.println(longName);
        if(orderToAdd.contains(longName)){
            String rightName = what + " ----- " + right + " x "  + Menu.menu.get(what) + " => " + right*Menu.menu.get(what);
            orderToAdd.set(orderToAdd.indexOf(longName),rightName);
        }else {
            System.out.println("Item not on order, ask customer again!");}
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/Orders/order-table-" + tableNumber + ".txt"));
            for (String s : orderToAdd){
                writer.write(s+"\n");
            }
            writer.close();
        }
        catch (Exception ex) { ex.printStackTrace(); }
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
