package AmandaNurOktaviani.JFood;
/**
 * Write a description of class Invoice here.
 *
 * @author (Amanda)
 * @version (27/02/2020)
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;
public abstract class Invoice
{
    // instance variables
    private int id;
    private ArrayList<Food> foods;
    private Calendar date;
    protected int totalPrice;
    private Customer customer;
    private InvoiceStatus invoiceStatus;

    /**
     * Constructor for objects of class Invoice
     * @param id, food, date, customer, invoiceStatus
     */
    public Invoice(int id, ArrayList<Food> foods, Customer customer)
    {
        this.id = id;
        this.foods = foods;
        this.customer = customer;
    }

    /**
     * Methods yang terdapat pada Class Invoice
     * Melakukan pemberian nilai dan pengambilan nilai value
     * Pengambilan nilai menggunakan getVariable()
     * Pemberian nilai dengan setVariable()
     */
    
    /**
     * mengambil nilai dari atribut id
     * @return id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * mengambil nilai dari atribut idFood
     * @return idFood
     */
    public ArrayList<Food> getFoods()
    {
        return foods;
    }
    
    /**
     * mengambil nilai dari atribut date
     * @return date
     */
    public Calendar getDate()
    {
        return date;
    }
    
    /**
     * mengambil nilai dari atribut totalPrice
     * @return totalPrice
     */
    public int getTotalPrice()
    {
        return totalPrice;
    }
    
    /**
     * mengambil nilai dari atribut customer
     * @return customer
     */
    public Customer getCustomer()
    {
        return customer;
    }
    
    /**
     * mengambil nilai dari atribut customer
     */
    public abstract PaymentType getPaymentType();
    
    /**
     * mengambil nilai dari atribut customer
     * @return invoiceStatus
     */
    public InvoiceStatus getInvoiceStatus()
    {
        return invoiceStatus;
    }
    
    /**
     * memberikan nilai pada atribut id
     * @param id - instance variabel yang digunakan untuk assign nilai id
     */
    public void setId (int id)
    {
        this.id = id;
    }
    
    /**
     * memberikan nilai pada atribut idFood
     * @param foods - instance variabel yang digunakan untuk assign nilai idFood
     */
    public void setFoods (ArrayList<Food> foods)
    {
        this.foods = foods;
    }
    
    /**
     * memberikan nilai pada atribut date
     * @param date - instance variabel yang digunakan untuk assign nilai date
     */
    public void setDate (Calendar date)
    {
        this.date = date;
    }
    
    public void setDate (int year, int month, int dayOfMonth)
    {
        this.date = new GregorianCalendar(year, month-1, dayOfMonth);
    }
    
    /**
     * memberikan nilai pada atribut totalPrice
     */
    public abstract void setTotalPrice();
    
    /**
     * memberikan nilai pada atribut customer
     * @param customer - instance variabel yang digunakan untuk assign nilai customer
     */
    public void setCustomer (Customer customer)
    {
        this.customer = customer;
    }
    
    public void setInvoiceStatus (InvoiceStatus invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }
    
    /**
     * fungsi untuk mengeprint data
     */
    /**public abstract void printData();*/
    
    public abstract String toString();

    public abstract void run();
    /**{
       String string = "";
       if(date != null)
       {
           Date tanggal = date.getTime();
           SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
           String tanggal1 = format.format(tanggal);
           string = "Invoice:\n" + "ID = " + id + "\n" + "Food = " + food.getName() + "\n" + 
           "Date = " + tanggal1 + "\n" + "Total Price = " + totalPrice + "\n" + "Customer = " + customer.getName() + "\n"
           + "Invoice Status = " + invoiceStatus;
       }
        
       else
       {    
           string = "Invoice:\n" + "ID = " + id + "\n" + "Food = " + food.getName() + "\n" + 
           "Total Price = " + totalPrice + "\n" + "Customer = " + customer.getName() + "\n"
           + "Invoice Status = " + invoiceStatus;  
       }
       
       System.out.println(string);
       return string;
    }*/
}
