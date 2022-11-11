import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static boolean isRunning = true;

    public static void main(String[] args) throws IOException {
        Functions func = new Functions();

        System.out.println("Hello and welcome to OpenOrder.");
        System.out.println("Please select from the menu below.");
        Scanner listen = new Scanner(System.in);
        while(isRunning){
            System.out.println("1.Orders");
            System.out.println("2.Administrative");
            System.out.println("3.Exit");
            String openingChoice = listen.nextLine();

            if(openingChoice.equals("1")){
                System.out.println("Goes into menu");
                String option = func.DisplayOptions();
                switch(option){
                    case "0":
                        break;
                    case "1":
                        int availability = func.GetOpenTables();
                        if (availability> 0 && availability <=5){
                            System.out.println("This is table "+availability);
                            Menu.loadMenu();
                            func.askForOrder(availability);
                        }
                        else{
                            System.out.println("No tables available!");}
                        break;
                    case "2":
                        System.out.println("Please choose a table");
                        int userInt = func.getUserInt();
                        Menu.loadMenu();
                        System.out.println("Select what to add to order. (If multiple items, type item#5(or other number)");
                        System.out.println("Type 'done' if ready.");
                        String userString = func.getUserString();
                        while(!userString.equals("done")){
                        Order.ToAdd(userInt,userString);
                        userString = func.getUserString();}
                        break;
                    case "3":
                        Menu.loadMenu("silent");
                        System.out.println("Please input the table number");
                        int userIntEdit = func.getUserInt();
                        Order.Display(userIntEdit);
                        System.out.println("Please input name of item that is wrong, right number, wrong number");

                        String userStringEdit = func.getUserString();
                        int userIntRight = func.getUserInt("General");
                        int userIntWrong = func.getUserInt("General");

                        Order.ToEdit(userIntEdit,userStringEdit,userIntRight,userIntWrong);
                        System.out.println("Order has been modified!");
                        break;
                    case "4":
                        Menu.loadMenu("silent");
                        System.out.println("Please input table number");
                        int userIntDelete = func.getUserInt();
                        Order.Display(userIntDelete);
                        System.out.println("Please input the name of the item you want to delete.");
                        String userStringDelete = func.getUserString();
                        Order.ToDelete(userIntDelete,userStringDelete);
                        break;
                    case "5":
                        System.out.println("Please input the table number to display order");
                        int orderToDisplay = func.getUserInt();
                        Order.Display(orderToDisplay);
                        break;
                    case "6":
                        System.out.println("Please select the table: ");
                        Order.Payment(func.getUserInt());
                        break;
                }
            }
            else if(openingChoice.equals("2")){
                int[] orders = {0,1,2,3,4,5};
                HashMap<String, Integer> order = Functions.addAllOrders(orders);
                Functions.updateReport(order);
                System.out.println("Report has been generated");
            }
            else if(openingChoice.equals("3")) {
                break;
            }
            else{
                System.out.println("Please choose from the menu (1 or 2).");
            }
        }


    }

}