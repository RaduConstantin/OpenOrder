import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static boolean isRunning = true;
    public static Table table1 = new Table();
    public static Table table2 = new Table();
    public static Table table3 = new Table();
    public static Table table4 = new Table();
    public static Table table5 = new Table();

    public static void main(String[] args) {
        Functions func = new Functions();

        System.out.println("Hello and welcome to OpenOrder.");
        System.out.println("Please select from the menu below.");
        Scanner listen = new Scanner(System.in);
        while(isRunning){
            System.out.println("1.Orders");
            System.out.println("2.Administrative");
            String openingChoice = listen.nextLine();

            if(openingChoice.equals("1")){
                System.out.println("Goes into menu");
                String option = func.DisplayOptions();
                switch(option){
                    case "0":
                        break;
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
                        break;
                }
            }
            else{
                System.out.println("Please choose from the menu (1 or 2).");
            }
        }

    }

}