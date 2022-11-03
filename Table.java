public class Table extends Order{
    boolean isOpen = false;
    int tableNumber;
    //constructor

    public Table(int number){
        super(number);
        tableNumber = number;
    }
    public boolean isOpen()
    {
        //if (payment is done for table == true){
        //isOpen = true;
        //esle{
        ///isOpen = false;
        return isOpen;
    }


}

