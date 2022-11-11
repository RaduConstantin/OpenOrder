import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.*;

public class Functions {
    //param


    //cons

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


    //meth
    public int GetTableNumber(){
        System.out.println("Please input table number");
        Scanner in = new Scanner(System.in);
        int tableNumber = in.nextInt();
        while(tableNumber<=1){
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
       if (tableNumber == 0){
            Order.Payment(0);
       }

    }}

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


    public static ArrayList<String> addAllOrders(int[] ints){

        ArrayList<String> items = new ArrayList<>();
        int numberOfItems;
        String itemName;
        for(int i : ints){
            ArrayList<String> eachorder = Order.ToOpen(i);
            for(String s:eachorder){
                if(s.contains("Order for table")){
                }else{
                    System.out.println("avem items");
                System.out.println(i);
                    String[] spliter = s.split("-----");
                System.out.println(spliter[0] + spliter[1]);
                    itemName = spliter[0].toString().trim();
                    String[] secondSpliter = spliter[1].split("x");
                    numberOfItems = Integer.parseInt(secondSpliter[0].trim());
//                    if(s.contains(itemName)){
//                        int indexOf = eachorder.indexOf(s);
//                        String[] lineExist = eachorder.get(indexOf).split("-");
//                        int existingNumber = Integer.parseInt(lineExist[1].trim());
//                        System.out.println(existingNumber);
//                        numberOfItems = numberOfItems + existingNumber;
//                        System.out.println(numberOfItems);
//                    }
                    items.add(itemName + " - " + numberOfItems);

            }}
        }
        return items;

    }

    in loc de lista foloseste coaie dict

    public static ArrayList<String> cleanupOrders(ArrayList<String> newList){
        for (String s : newList){

        }
    }


    }

