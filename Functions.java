import java.lang.reflect.Array;
import java.util.*;

public class Functions {
    //param


    //cons


    //meth
    public String DisplayOptions(){
        System.out.println("0. Back to main menu.");
        System.out.println("1. New order.");
        System.out.println("2. Add items to order.");
        System.out.println("3. Edit items from order.");
        System.out.println("4. Delete items from order.");
        System.out.println("5. Display order.");
        Scanner listen = new Scanner(System.in);
        String orderOptions = listen.nextLine();
        String[] options = new String[]{"0", "1", "2", "3", "4", "5"};
        while(!Arrays.asList(options).contains(orderOptions)){
            System.out.println("Please select from the mentioned options. (1 or 2 or 3 etc)");
            orderOptions = listen.nextLine();
        }
        return orderOptions;
    }


    public int GetOpenTables(Table... args){
        List<Table> tables = new ArrayList<Table>();

        Collections.addAll(tables, args);
        for(Table i : tables){
            if(i.isOpen()){
                return i.tableNumber;
            }
        }
        return 0;

    }


    public int HereOrToGo(int option)
    {
        if(option == 0){
            System.out.println("Sorry, currently all our tables are full, would you like to order to go?");
            Scanner listen = new Scanner(System.in);
            //boolean correctInput = true;
            while(true){
                String choice = listen.nextLine().toLowerCase();
                if (choice.equals("yes")){
                    System.out.println("Customer chose to order to go");
                    return 0;
                }
                else if (choice.equals("no")){
                    System.out.println("We are sorry, maybe you can come back later.");
                    return 100;
                }
                else
                {
                    System.out.println("Please answer with yes or no!");
                    System.out.println("Would you like your order to go?");
                }
            }

        }
        else{return option;}
    }
}
