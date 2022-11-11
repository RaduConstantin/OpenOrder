import java.io.*;
import java.util.Objects;

public class MainTest {
    public static void main(String[] args) throws IOException {
        Menu.loadMenu();
        int[] orders = {0,1,2,3,4,5};
        System.out.println(Functions.addAllOrders(orders));
        Functions.updateReport(Functions.addAllOrders(orders));

    }}


