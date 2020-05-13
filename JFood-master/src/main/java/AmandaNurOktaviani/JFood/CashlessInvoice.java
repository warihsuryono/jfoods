package AmandaNurOktaviani.JFood;
/**
 * Write a description of class CashlessInvoice here.
 *
 * @author (Amanda)
 * @version (12/03/2020)
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class CashlessInvoice extends Invoice
{
    // instance variables - replace the example below with your own
    private static final PaymentType PAYMENT_TYPE=PaymentType.Cashless;
    private Promo promo;

    /**
     * Constructor for objects of class CashlessInvoice
     */
    public CashlessInvoice(int id, ArrayList<Food> foods, Customer customer)
    {
        super(id, foods, customer);
    }

    public CashlessInvoice(int id, ArrayList<Food> foods, Customer customer, Promo promo)
    {
        super(id, foods, customer);
        this.promo = promo;
    }


    /**
     * An example of a method - replace this comment with your own
     * @return PAYMENT_TYPE
     */
    public PaymentType getPaymentType()
    {
        return PAYMENT_TYPE;
    }

    public Promo getPromo()
    {
        return promo;
    }

    public void setPromo(Promo promo)
    {
        this.promo = promo;
    }

    public void setTotalPrice()
    {
        for(Food foodList:getFoods())
        {
            super.totalPrice = totalPrice + foodList.getPrice();
        }
        if (promo!=null && promo.getActive()==true &&
            totalPrice > promo.getMinPrice())
            {
                super.totalPrice = totalPrice - promo.getDiscount();
            }
        else
        {
            super.totalPrice = totalPrice;
        }
    }

    /**public void printData()
    {
        if (promo!=null)
        {
            if(totalPrice >= promo.getMinPrice())
            {
                System.out.println("========INVOICE========");
                System.out.println("ID: " + super.getId());
                System.out.println("Food: " + super.getFood().getName());
                System.out.println("Date: " + super.getDate());
                System.out.println("Customer: " + super.getCustomer().getName());
                System.out.println("Promo: " + getPromo().getCode());
                System.out.println("Total price: " + totalPrice);
                System.out.println("Status: " + super.getInvoiceStatus());
                System.out.println("Payment Type: " + PAYMENT_TYPE);
            }
            else{
                System.out.println("========INVOICE========");
                System.out.println("ID: " + super.getId());
                System.out.println("Food: " + super.getFood().getName());
                System.out.println("Date: " + super.getDate());
                System.out.println("Customer: " + super.getCustomer().getName());
                System.out.println("Total price: " + totalPrice);
                System.out.println("Status: " + super.getInvoiceStatus());
                System.out.println("Payment Type: " + PAYMENT_TYPE);
            }
        }
        else
        {
            System.out.println("========INVOICE========");
            System.out.println("ID: " + super.getId());
            System.out.println("Food: " + super.getFood().getName());
            System.out.println("Date: " + super.getDate());
            System.out.println("Customer: " + super.getCustomer().getName());
            System.out.println("Total price: " + totalPrice);
            System.out.println("Status: " + super.getInvoiceStatus());
            System.out.println("Payment Type: " + PAYMENT_TYPE);
        }
    }*/
    @Override
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String foods = "";
        String months[] = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (Food foodlist:getFoods())
        {
            foods = foods += foodlist.getName() + ",";
        }
        foods = foods.substring(0, foods.length() - 2);
        if(promo!=null && promo.getActive() && totalPrice>promo.getMinPrice())
        {
            return "==========INVOICE==========" +
                    "\nID: " + getId() +
                    "\nFood: " + foods +
                    "\nDate: " + sdf.format(getDate().getTime()) +
                    "\nCustomer: " + getCustomer().getName() +
                    "\nPromo: " + promo.getCode() +
                    "\nTotal Price: " + super.totalPrice +
                    "\nStatus: " + getInvoiceStatus() +
                    "\nPayment Type: " + PAYMENT_TYPE;
        }
        else
        {
            return "==========INVOICE==========" +
                    "\nID: " + getId() +
                    "\nFood: " + foods +
                    "\nDate: " + sdf.format(getDate().getTime()) +
                    "\nCustomer: " + getCustomer().getName() +
                    "\nTotal Price: " + super.totalPrice +
                    "\nStatus: " + getInvoiceStatus() +
                    "\nPayment Type: " + PAYMENT_TYPE;
        }
    }

    public void start()
    {
        for(Food foodList:getFoods())
        {
            super.totalPrice += totalPrice + foodList.getPrice();
        }
        if (promo!=null && promo.getActive()==true &&
                totalPrice > promo.getMinPrice())
        {
            super.totalPrice = totalPrice - promo.getDiscount();
        }
        else
        {
            super.totalPrice = totalPrice;
        }
    }

    @Override
    public void run()
    {

    }

}
    
   


