import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static boolean isRunning = true;
    public static Table toGo = new Table(0);
    public static Table table1 = new Table(1);
    public static Table table2 = new Table(2);
    public static Table table3 = new Table(3);
    public static Table table4 = new Table(4);
    public static Table table5 = new Table(5);

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
                        int availability = func.GetOpenTables(table1,table2,table3,table4,table5);
                        int hereOrToGo = func.HereOrToGo(availability);
                        if (hereOrToGo>= 0 && hereOrToGo <=5){

                        }
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