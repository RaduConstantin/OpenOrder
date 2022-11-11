import java.io.*;
import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.*;

public class Functions {

    public String getUserString(){
        Scanner in = new Scanner(System.in);
        String tableNumber = in.nextLine();
        return tableNumber;

}


    public int getUserInt(){
        Scanner in = new Scanner(System.in);
        int tableNumber = in.nextInt();
        while(tableNumber > 7 || tableNumber < 0){
            System.out.println("Please choose a valid number");
            in = new Scanner(System.in);
            tableNumber = in.nextInt();
        }
        return tableNumber;
    }

    public int getUserInt(String gen){
        Scanner in = new Scanner(System.in);
        int tableNumber = in.nextInt();
        while(tableNumber <0){
            System.out.println("Please choose a valid number");
            in = new Scanner(System.in);
            tableNumber = in.nextInt();
        }
        return tableNumber;
    }

    public String DisplayOptions(){
        System.out.println("0. Back to main menu.");
        System.out.println("1. New order.");
        System.out.println("2. Add items to order.");
        System.out.println("3. Edit items from order.");
        System.out.println("4. Delete items from order.");
        System.out.println("5. Display order.");
        System.out.println("6. Pay order.");
        Scanner listen = new Scanner(System.in);
        String orderOptions = listen.nextLine();
        String[] options = new String[]{"0", "1", "2", "3", "4", "5","6"};
        while(!Arrays.asList(options).contains(orderOptions)){
            System.out.println("Please select from the mentioned options. (1 or 2 or 3 etc)");
            orderOptions = listen.nextLine();
        }
        return orderOptions;
    }

    public int GetOpenTables(){

        for(int i = 1; i <=5; i++){
            if(Order.isPaid(i)){
                return i;
            }
        }
        return 0;

    }



    public void askForOrder(int tableNumber){
        System.out.println("What would you like to order?(Enter the words from the menu separated by enter key)");
        boolean continueToOrder = true;
        while(continueToOrder){
            Scanner scan = new Scanner(System.in);
            String option = scan.nextLine().toLowerCase();
            if(option.equals("no")){
                continueToOrder = false;
                System.out.println("Thank you, I will be right back with your order!");
            }else {
            Order.ToAdd(tableNumber, option);
            System.out.println("Noted, anything else? (answer with 'no' to finish ordering)");}
       }

    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }


    public static HashMap<String,Integer> addAllOrders(int[] ints){
        HashMap<String, Integer> combinedOrders = new HashMap<>();
        int numberOfItems;
        String itemName;
        for(int i : ints){
            ArrayList<String> eachorder = Order.ToOpen(i);
            for(String s:eachorder){
                if(s.contains("Order for table")){
                }else{
                    String[] spliter = s.split("-----");

                    itemName = spliter[0].trim();
                    String[] secondSpliter = spliter[1].split("x");
                    numberOfItems = Integer.parseInt(secondSpliter[0].trim());
                    if(combinedOrders.containsKey(itemName)){
                        combinedOrders.put(itemName,combinedOrders.get(itemName)+numberOfItems);
                    }
                    else{
                        combinedOrders.put(itemName,numberOfItems);
                    }
            }}
        }
        LinkedHashMap<String,Integer> orderedMap = new LinkedHashMap<>();
        combinedOrders.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> orderedMap.put(x.getKey(), x.getValue()));


        return orderedMap;

    }


    public static void updateReport(HashMap<String, Integer> map){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/Reports/numberOfItemsSold.txt"));
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                writer.write(key + " - " + value + "\n");
            }
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}

