import java.io.*;
import java.sql.SQLOutput;
import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {

    public static Map<String, Double> menu = new LinkedHashMap<String, Double>();

    public static void loadMenu() throws IOException {

        //open the menu selected by the user (type of menu is selectable by user)
        try{
        FileInputStream fluxIn = new FileInputStream("./src/menu.txt");
        InputStreamReader isr = new InputStreamReader(fluxIn);
        BufferedReader bufferIn = new BufferedReader(isr);
        String line;
        while((line = bufferIn.readLine()) != null){
            String[] columns = line.split("=");
            System.out.println(columns[0].trim() +"     " + Double.parseDouble(columns[1].trim()));
            menu.put(columns[0].trim(), Double.parseDouble(columns[1].trim()));
            System.out.println(columns[0].replace("_", " ") + " - " + columns[1] + " RON");

        }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry we do not have that type of menu.");

        }

    }
}