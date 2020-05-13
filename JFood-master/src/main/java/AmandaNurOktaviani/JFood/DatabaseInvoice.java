package AmandaNurOktaviani.JFood;
import java.util.ArrayList;

public class DatabaseInvoice {
    private static final ArrayList<Invoice> INVOICE_DATABASE = new ArrayList<Invoice>();
    private static int lastId = 0;

    public static ArrayList<Invoice> getInvoiceDatabase()
    {
        return INVOICE_DATABASE;
    }

    public static int getLastId()
    {
        return lastId;
    }

    public static Invoice getInvoiceById(int id) throws InvoiceNotFoundException
    {
        for(Invoice invoice:INVOICE_DATABASE)
        {
            if(invoice.getId()==id)
            {
                return invoice;
            }
        }
        throw new InvoiceNotFoundException(id);
    }

    public static ArrayList<Invoice> getInvoiceByCustomer(int customerId) throws CustomerNotFoundException
    {
        ArrayList<Invoice> list = new ArrayList<Invoice>();
        Customer customer = DatabaseCustomer.getCustomerById(customerId);
        for (Invoice invoice:INVOICE_DATABASE)
        {
            if(customer.equals(invoice.getCustomer()))
            {
                list.add(invoice);
            }
        }
        return list;
    }

    public static boolean addInvoice(Invoice invoice) throws OngoingInvoiceAlreadyExistsException
    {
        int customerId = invoice.getCustomer().getId();
        for(Invoice invoice1:INVOICE_DATABASE)
        {
            if(invoice1.getCustomer().getId()==customerId && invoice1.getInvoiceStatus()==InvoiceStatus.Ongoing)
            {
                throw new OngoingInvoiceAlreadyExistsException(invoice);
            }
        }
        INVOICE_DATABASE.add(invoice);
        lastId=invoice.getId();
        return true;
    }

    public static boolean changeInvoiceStatus(int id, InvoiceStatus invoiceStatus)
    {
        for (Invoice invoice2: INVOICE_DATABASE)
        {
            if (invoice2.getId()==id && invoice2.getInvoiceStatus()==InvoiceStatus.Ongoing)
            {
                invoice2.setInvoiceStatus(invoiceStatus);
                return true;
            }
        }
        return false;
    }

    public static boolean removeInvoice(int id) throws InvoiceNotFoundException
    {
        for(Invoice invoice : INVOICE_DATABASE)
        {
            if(invoice.getId()==id)
            {
                INVOICE_DATABASE.remove(invoice);
                return true;
            }
        }
        throw new InvoiceNotFoundException(id);
    }
}
