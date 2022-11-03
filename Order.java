import java.io.*;
import java.util.Scanner;


//order is used to manipulate the orders from tables
public class Order {

    int tableNumber;

    public Order(int number) {
        this.tableNumber = number;

    }

    public void ToAdd(String arg) {
        try {
            FileWriter myWriter = new FileWriter("./src/Orders/order-table-" + tableNumber + ".txt", true);
            myWriter.write("\n" + arg);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error1");
            e.printStackTrace();
        }
    }

    public void ToCreate() {
        try {
            File myOrder = new File("./src/Orders/order-table-" + tableNumber + ".txt");
            if (myOrder.createNewFile()) {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean ToDelete(String arg) throws IOException {
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

    public void justprint(){
        System.out.println(tableNumber);
    }

}
