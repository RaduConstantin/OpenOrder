import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

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

}
