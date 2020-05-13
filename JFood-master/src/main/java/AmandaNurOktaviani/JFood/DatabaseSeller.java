package AmandaNurOktaviani.JFood;
import java.sql.*;
import java.util.ArrayList;
/**
 * Write a description of class DatabaseSeller here.
 *
 * @author (Amanda)
 * @version (28/02/2020)
 */
public class DatabaseSeller
{
    private static Connection conn = DatabaseConnectionPostgre.connection();
    private static Statement stmt = null;

    // instance variables
    private static final ArrayList<Seller> SELLER_DATABASE = new ArrayList<Seller>();
    private static int lastId = 0;

    public static ArrayList<Seller> getSellerDatabase()
    {
        return SELLER_DATABASE;
    }

    public static int getLastId()
    {
        return lastId;
    }

    public static Seller getSellerById(int id) throws SellerNotFoundException
    {
//        for(Seller seller:SELLER_DATABASE)
//        {
//            if(seller.getId()==id)
//            {
//                return seller;
//            }
//        }
        try {
            stmt = conn.createStatement();
            ResultSet sellers = stmt.executeQuery("SELECT * FROM sellers  WHERE id='" + id + "'");
            if(sellers.next()) {
                Location location = new Location(sellers.getString("city"), sellers.getString("province"), "Lokasi");
                return new Seller(id, sellers.getString("nama"), sellers.getString("email"), sellers.getString("phoneNumber"), location);
            } else {
                return null;
            }

        } catch (Exception e) { }
        throw new SellerNotFoundException(id);
    }

    public static boolean addSeller(Seller seller)
    {
//        SELLER_DATABASE.add(seller);
//        lastId=seller.getId()+1;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT id FROM sellers  WHERE email='"+ seller.getEmail() +"'";
            ResultSet sellers = stmt.executeQuery(sql);
            if(!sellers.next()) { //cek apakah email sudah ada di table sellers, jika belum insert sellers
                sql = "INSERT INTO sellers (nama,email,phoneNumber,city,province,created_at) VALUES ('" + seller.getName() + "','" + seller.getEmail() + "','" + seller.getPhoneNumber() + "','" + seller.getLocation().getCity() + "','" + seller.getLocation().getProvince() + "',CURRENT_TIMESTAMP);";
                PreparedStatement prest;
                prest = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prest.executeUpdate();
                ResultSet rs = prest.getGeneratedKeys();
                if(rs.next()) {
                    lastId = rs.getInt("id") + 1;
                }
            } else {
                lastId = sellers.getInt("id") + 1;
            }
        } catch (Exception e) { }
        return true;
    }

    public static boolean removeSeller(int id) throws SellerNotFoundException
    {
//        for(int i=0; i<SELLER_DATABASE.size(); i++)
//        {
//            Seller seller = SELLER_DATABASE.get(i);
//            if(seller.getId()==id)
//            {
//                SELLER_DATABASE.remove(i);
//                return true;
//            }
//        }
        try {
            stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM sellers WHERE id='" + id + "'");
            return true;
        } catch (Exception e) { }
        throw new SellerNotFoundException(id);
    }
}
