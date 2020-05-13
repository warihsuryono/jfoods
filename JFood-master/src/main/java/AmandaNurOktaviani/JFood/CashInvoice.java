package AmandaNurOktaviani.JFood;
/**
 * Write a description of class CashInvoice here.
 *
 * @author (Amanda)
 * @version (13/03/2020)
 */
import java.text.SimpleDateFormat;
import java.util.*;
public class CashInvoice extends Invoice
{
    // instance variables
    private static final PaymentType PAYMENT_TYPE=PaymentType.Cash;
    private int deliveryFee;

    /**
     * Constructor for objects of class CashInvoice
     */
    public CashInvoice(int id, ArrayList<Food> foods, Customer customer)
    {
        super(id, foods, customer);
    }

    public CashInvoice(int id, ArrayList<Food> foods, Customer customer, int deliveryFee)
    {
        super(id, foods, customer);
        this.deliveryFee = deliveryFee;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param
     * @return    the sum of x and y
     */
    public PaymentType getPaymentType()
    {
        return PAYMENT_TYPE;
    }
    
    public int getDeliveryFee()
    {
        return deliveryFee;
    }
    
    public void setDeliveryFee (int deliveryFee)
    {
        this.deliveryFee = deliveryFee;
    }
    
    public void setTotalPrice()
    {
        super.totalPrice=0;
        for(Food foodList:getFoods())
        {
            super.totalPrice = totalPrice + foodList.getPrice();
        }
            super.totalPrice = super.totalPrice + deliveryFee;
    }
    
    /**public void printData()
    {
        if (deliveryFee!=0)
        {
            System.out.println("========INVOICE========");
            System.out.println("ID: " + super.getId());
            System.out.println("Food: " + super.getFood().getName());
            System.out.println("Date: " + super.getDate());
            System.out.println("Customer: " + super.getCustomer().getName());
            System.out.println("Delivery Fee: " + deliveryFee);
            System.out.println("Total price: " + totalPrice);
            System.out.println("Status: " + super.getInvoiceStatus());
            System.out.println("Payment Type: " + PAYMENT_TYPE);
            }
        else
        {
            System.out.println("========INVOICE========");
            System.out.println("ID: " + super.getId());
            System.out.println("Food: " + super.getFood().getName());
            System.out.println("Date: " + super.getDate());
            System.out.println("Customer: " + super.getCustomer().getName());
            System.out.println("Delivery Fee: " + deliveryFee);
            System.out.println("Total price: " + totalPrice);
            System.out.println("Status: " + super.getInvoiceStatus());
            System.out.println("Payment Type: " + PAYMENT_TYPE);
        }
    }*/

    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String foods = "";
        String months[] = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Oct", "Nov", "Dec"};
        for(Food foodlist:getFoods())
        {
            foods = foods + foodlist.getName() + ",";
        }
        foods = foods.substring(0, foods.length() - 2);
        return "==========INVOICE==========" +
                "\nID: " + getId() +
                "\nFood: " + foods +
                "\nDate: " + sdf.format(getDate().getTime()) +
                "\nCustomer: " + getCustomer().getName() +
                "\nDelivery Fee: " + getDeliveryFee() +
                "\nTotal Price: " + totalPrice +
                "\nStatus: " + getInvoiceStatus() +
                "\nPayment Type: " + PAYMENT_TYPE;
    }

    @Override
    public void run()
    {

    }

}
