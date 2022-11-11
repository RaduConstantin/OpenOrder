import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;



//order is used to manipulate the orders from tables
public class Order {
    public static boolean Payment(int tableNumber) {

        double finalVal = 0;

        ArrayList<String> orderToAdd = ToOpen(tableNumber);

        String[] a;
        for(String s: orderToAdd){
            if(s.contains("=>")){
            a = s.split("=>");
            System.out.println(Arrays.toString(a));
            double value = Double.parseDouble(a[1].trim());
            finalVal = finalVal + value;
            }
        }
        System.out.println("Collected payment: " + String.format("%.2f",finalVal));
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
    public static void WriteTO(ArrayList<String> list, int tableNumber){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/Orders/order-table-" + tableNumber + ".txt"));
            for (String s : list) {
                if(s.contains("_")){
                    s = s.replace("_"," ");
                }
                writer.write(s + "\n");
            }
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // meth to add to orders
    public static void ToAdd(int tableNumber, String arg) {
        if(arg.contains(" ")){
           arg =  arg.replace(" ","_");
        }
        ArrayList<String> orderToAdd = ToOpen(tableNumber);
        int multiplier = 1;
        String orderName;
        // split multiple order by #
        if (arg.contains("#")) {
            String[] orderPart = arg.split("#");
            orderName = orderPart[0].trim().toLowerCase();
            if (Functions.isInteger(orderPart[1].trim())) {
                multiplier = Integer.parseInt(orderPart[1].trim());
            }
        } else orderName = arg;
        String newFullOrder = "";
        String fullOrder;

        if(Menu.menu.containsKey(orderName)){

            fullOrder = orderName + " ----- " + multiplier + " x " + Menu.menu.get(orderName) + " => " + multiplier * Menu.menu.get(orderName);
            for (String d : orderToAdd) {
                if (d.contains(orderName)) {
                    String[] newLine = d.split( "-----" );
                    String[] oldMultiplierArr = newLine[1].split("x");
                    int oldMultiplier = Integer.parseInt(oldMultiplierArr[0].trim());
                    newFullOrder = orderName + " ----- " + (multiplier + oldMultiplier) + " x " + Menu.menu.get(orderName) + " => " + (oldMultiplier+multiplier) * Menu.menu.get(orderName);
                    int index = orderToAdd.indexOf(d);
                    orderToAdd.set(index,newFullOrder);
                }
            }
            if(newFullOrder.equals("")){
                orderToAdd.add(fullOrder);
            }
        }

        Order.WriteTO(orderToAdd,tableNumber);

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

    public static void Clear(int tableNumber){
        try{
            FileWriter myWriter = new FileWriter("./src/Orders/order-table-" + tableNumber + ".txt");
            myWriter.write("Order for table " + tableNumber);
            myWriter.close();
        }catch (IOException e){
            System.out.println();
        }

    }

    public static void ToEdit(int tableNumber, String what, int right, int wrong){
        ArrayList<String> orderToAdd = ToOpen(tableNumber);
        String longName = what + " ----- " + wrong + " x "  + Menu.menu.get(what) + " => " + wrong*Menu.menu.get(what);
        if(orderToAdd.contains(longName)){
            System.out.println("Found order");
            String rightName = what + " ----- " + right + " x "  + Menu.menu.get(what) + " => " + right*Menu.menu.get(what);
            orderToAdd.set(orderToAdd.indexOf(longName),rightName);
        }else {
            System.out.println("Item not on order, ask customer again!");}

        Order.WriteTO(orderToAdd,tableNumber);
    }

    public static void ToDelete(int tableNumber, String arg) throws IOException {
        ArrayList<String> orderToAdd = ToOpen(tableNumber);

        int indexOfDelete = -1;
        for(String s : orderToAdd){
            if(s.contains(arg)){
                indexOfDelete = orderToAdd.indexOf(s);
            }
        }
        if(indexOfDelete>0){
        orderToAdd.remove(indexOfDelete);
            System.out.println("Item deleted!");}

        Order.WriteTO(orderToAdd,tableNumber);
    }

    public static void Display(int tableNumber) {
        ArrayList<String> orderToAdd = ToOpen(tableNumber);
        for(String s : orderToAdd){
            System.out.println(s);
            }
        }

    public static boolean isPaid(int tableNumber){
        ArrayList<String> orderToAdd = ToOpen(tableNumber);
        int counter = 0;
        for (String s : orderToAdd){
            if(!s.isBlank()){
                counter++;
            }
        }
        if (counter==1){
            return true;
        }
        else return false;
    }
}
