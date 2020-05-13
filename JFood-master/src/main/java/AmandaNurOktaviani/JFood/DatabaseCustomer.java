package AmandaNurOktaviani.JFood;
import java.util.ArrayList;
/**
 * Write a description of class DatabaseCustomer here.
 *
 * @author (Amanda)
 * @version (05/03/2020)
 */
public class DatabaseCustomer
{
    // instance variables - replace the example below with your own
    private static final ArrayList<Customer> CUSTOMER_DATABASE = new ArrayList<Customer>();
    private static int lastId=0;

    public static ArrayList<Customer> getCustomerDatabase()
    {
        return CUSTOMER_DATABASE;
    }

    public static int getLastId()
    {
        return lastId;
    }

    public static Customer getCustomerById(int id) throws CustomerNotFoundException
    {
        for(Customer customer:CUSTOMER_DATABASE)
        {
            if(customer.getId()==id)
            {
                return customer;
            }
        }
        throw new CustomerNotFoundException(id);
    }

    public static boolean addCustomer(Customer customer) throws EmailAlreadyExistsException
    {
        for(Customer _customer:CUSTOMER_DATABASE)
        {
            if(_customer.getEmail().equals(customer.getEmail()))
            {
                throw new EmailAlreadyExistsException(customer);
            }
        }
        CUSTOMER_DATABASE.add(customer);
        lastId=customer.getId();
        return true;
    }

    public static boolean removeCustomer(int id) throws CustomerNotFoundException {
        for (int i = 0; i < CUSTOMER_DATABASE.size(); i++) {
            Customer customer = CUSTOMER_DATABASE.get(i);
            if (customer.getId() == id) {
                CUSTOMER_DATABASE.remove(i);
                return true;
            }
        }
        throw new CustomerNotFoundException(id);
    }

    public static Customer getCustomerLogin(String email, String password)
    {
        for(Customer customer:CUSTOMER_DATABASE)
        {
            if(customer.getEmail().equals(customer.getEmail()) && customer.getPassword().equals(customer.getPassword()))
            {
                return customer;
            }
        }
        return null;
    }
}

