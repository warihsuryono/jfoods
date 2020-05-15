package AmandaNurOktaviani.JFood;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseInvoicePostgre {
    private static Connection conn = DatabaseConnectionPostgre.connection();
    private static Statement stmt = null;
    private static int lastId = 0;

    public static ArrayList<Invoice> getInvoiceDatabase()
    {
        ArrayList<Invoice> list = new ArrayList<Invoice>();
        ArrayList<Food> foods = new ArrayList<Food>();
        int current_invoice_id = 0;
        int invoice_id = 0;
        int customer_id = 0;
        int food_id = 0;
        try {
            stmt = conn.createStatement();
            ResultSet invoices = stmt.executeQuery("SELECT * FROM invoices order by invoice_id");
            while(invoices.next()) {
                invoice_id = invoices.getInt("invoice_id");
                customer_id = invoices.getInt("customer_id");
                food_id = invoices.getInt("food_id");
                if(current_invoice_id != invoice_id){
                    if(current_invoice_id != 0){
                        Customer customer = DatabaseCustomerPostgre.getCustomerById(customer_id);
                        Invoice invoice = new Invoice(invoice_id, foods, customer) {
                            @Override
                            public PaymentType getPaymentType() {
                                return null;
                            }

                            @Override
                            public void setTotalPrice() {

                            }

                            @Override
                            public String toString() {
                                return null;
                            }

                            @Override
                            public void run() {

                            }
                        };
                        list.add(invoice);
                    }
                    current_invoice_id = invoice_id;
                    foods.clear();
                }
                Food food = DatabaseFoodPostgre.getFoodById(food_id);
                foods.add(food);
            }
            Customer customer = DatabaseCustomerPostgre.getCustomerById(customer_id);
            Invoice invoice = new Invoice(invoice_id, foods, customer) {
                @Override
                public PaymentType getPaymentType() {
                    return null;
                }

                @Override
                public void setTotalPrice() {

                }

                @Override
                public String toString() {
                    return null;
                }

                @Override
                public void run() {

                }
            };
            list.add(invoice);
        } catch (Exception e) { }
        return list;
    }

    public static int getLastId()
    {
        return lastId;
    }

    public static Invoice getInvoiceById(int id) throws InvoiceNotFoundException
    {
        ArrayList<Food> foods = new ArrayList<Food>();
        int customer_id = 0;
        try {
            stmt = conn.createStatement();
            ResultSet invoices = stmt.executeQuery("SELECT * FROM invoices WHERE invoice_id='" + id + "'");
            while(invoices.next()) {
                customer_id = invoices.getInt("customer_id");
                Food food = DatabaseFoodPostgre.getFoodById(invoices.getInt("food_id"));
                foods.add(food);
            }
            Customer customer = DatabaseCustomerPostgre.getCustomerById(customer_id);
            Invoice invoice = new Invoice(id, foods, customer) {
                @Override
                public PaymentType getPaymentType() {
                    return null;
                }

                @Override
                public void setTotalPrice() {

                }

                @Override
                public String toString() {
                    return null;
                }

                @Override
                public void run() {

                }
            };
            return invoice;
        } catch (Exception e) { return null; }
    }

    public static ArrayList<Invoice> getInvoiceByCustomer(int customerId) throws CustomerNotFoundException
    {
        ArrayList<Invoice> list = new ArrayList<Invoice>();
        ArrayList<Food> foods = new ArrayList<Food>();
        int current_invoice_id = 0;
        int invoice_id = 0;
        int customer_id = 0;
        int food_id = 0;
        try {
            stmt = conn.createStatement();
            ResultSet invoices = stmt.executeQuery("SELECT * FROM invoices WHERE customer_id='" + customerId + "' order by invoice_id");
            while(invoices.next()) {
                invoice_id = invoices.getInt("invoice_id");
                customer_id = invoices.getInt("customer_id");
                food_id = invoices.getInt("food_id");
                if(current_invoice_id != invoice_id){
                    if(current_invoice_id != 0){
                        Customer customer = DatabaseCustomerPostgre.getCustomerById(customer_id);
                        Invoice invoice = new Invoice(invoice_id, foods, customer) {
                            @Override
                            public PaymentType getPaymentType() {
                                return null;
                            }

                            @Override
                            public void setTotalPrice() {

                            }

                            @Override
                            public String toString() {
                                return null;
                            }

                            @Override
                            public void run() {

                            }
                        };
                        list.add(invoice);
                    }
                    foods.clear();
                    current_invoice_id = invoice_id;
                }
                Food food = DatabaseFoodPostgre.getFoodById(food_id);
                foods.add(food);
            }
            Customer customer = DatabaseCustomerPostgre.getCustomerById(customer_id);
            Invoice invoice = new Invoice(invoice_id, foods, customer) {
                @Override
                public PaymentType getPaymentType() {
                    return null;
                }

                @Override
                public void setTotalPrice() {

                }

                @Override
                public String toString() {
                    return null;
                }

                @Override
                public void run() {

                }
            };
            list.add(invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean addInvoice(Invoice invoice, String promoCode) throws OngoingInvoiceAlreadyExistsException
    {
        int customerId = invoice.getCustomer().getId();
        int invoice_id = 0;
        int promocode = 0;
        String sql = "";
        try {
            stmt = conn.createStatement();
            ResultSet invoices = stmt.executeQuery("SELECT invoice_id FROM invoices order by invoice_id DESC LIMIT 1");
            if(invoices.next()) {
                invoice_id = invoices.getInt("invoice_id");
            } else {
                invoice_id = 1;
            }

            invoices = stmt.executeQuery("SELECT invoice_id FROM invoices WHERE customer_id = '" + invoice.getCustomer().getId() + "' AND status='Ongoing' LIMIT 1");
            if(invoices.next()) {//ambil invoice dari status yg masih ongoing
                invoice_id = invoices.getInt("invoice_id");
            }

            ArrayList<Food> foods = invoice.getFoods();
            for (Food food:foods) {
                int food_id = food.getId();
                int price = food.getPrice();
                int total_price = price;
                if (!promoCode.equals("")){
                    promocode = Integer.parseInt(promoCode);
                    Promo promo = DatabasePromoPostgre.getPromoByCode(promoCode);
                    boolean active = promo.getActive();
                    int minPrice = promo.getMinPrice();
                    int discount = promo.getDiscount();
                    if(active && minPrice <= price){
                        total_price = price - discount;
                    }
                }
                sql = "INSERT INTO invoices (invoice_id,customer_id,food_id,payment_type,price,promocode,total_price,status,created_at) VALUES ('" + invoice_id +"','" + customerId +"','" + food_id +"','" + invoice.getPaymentType().toString() +"','" + price +"','" + promocode +"','" + total_price + "','Ongoing',CURRENT_TIMESTAMP);";
                stmt.executeQuery(sql);
            }
        } catch (Exception e) { }
        lastId=invoice_id + 1;
        return true;
    }

    public static boolean changeInvoiceStatus(int id, InvoiceStatus invoiceStatus)
    {
        try {
            stmt = conn.createStatement();
            stmt.executeQuery("UPDATE invoices SET status = '" + invoiceStatus.toString() + "' WHERE invoice_id='" + id + "'");
            return true;
        } catch (Exception e) { }
        return false;
    }

    public static boolean removeInvoice(int id) throws InvoiceNotFoundException
    {
        try {
            stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM invoices WHERE invoice_id='" + id + "'");
            return true;
        } catch (Exception e) { }
        throw new InvoiceNotFoundException(id);
    }
}
