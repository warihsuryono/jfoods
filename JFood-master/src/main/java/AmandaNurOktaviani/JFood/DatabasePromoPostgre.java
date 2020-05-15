package AmandaNurOktaviani.JFood;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Write a description of class DatabasePromo here.
 *
 * @author (Amanda)
 * @version (12/03/2020)
 */
public class DatabasePromoPostgre
{
    private static Connection conn = DatabaseConnectionPostgre.connection();
    private static Statement stmt = null;
    // instance variables
    private static int lastId = 0;

    public static ArrayList<Promo> getPromoDatabase()
    {
        ArrayList<Promo> list = new ArrayList<Promo>();
        try {
            stmt = conn.createStatement();
            ResultSet promos = stmt.executeQuery("SELECT * FROM promos");
            while(promos.next()) {
                Promo promo = new Promo(promos.getInt("id"), promos.getString("code"), promos.getInt("discount"), promos.getInt("minPrice"), promos.getBoolean("active"));
                list.add(promo);
            }
        } catch (Exception e) { }
        return list;
    }

    public static int getLastId()
    {
        return lastId;
    }

    public static Promo getPromoById(int id) throws PromoNotFoundException
    {
        try {
            stmt = conn.createStatement();
            ResultSet promos = stmt.executeQuery("SELECT * FROM promos WHERE id='" + id + "'");
            if(promos.next()) {
                return new Promo(promos.getInt("id"), promos.getString("code"), promos.getInt("discount"), promos.getInt("minPrice"), promos.getBoolean("active"));
            } else {
                return null;
            }

        } catch (Exception e) { }
        throw new PromoNotFoundException(id);
    }

    public static Promo getPromoByCode(String code)
    {
        try {
            stmt = conn.createStatement();
            ResultSet promos = stmt.executeQuery("SELECT * FROM promos WHERE code='" + code + "'");
            if(promos.next()) {
                return new Promo(promos.getInt("id"), promos.getString("code"), promos.getInt("discount"), promos.getInt("minPrice"), promos.getBoolean("active"));
            } else {
                return null;
            }

        } catch (Exception e) { }
        return null;
    }

    public static boolean addPromo(Promo promo) throws PromoCodeAlreadyExistsException
    {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT id FROM promos  WHERE code='"+ promo.getCode() +"'";
            ResultSet promos = stmt.executeQuery(sql);
            if(!promos.next()) {
                sql = "INSERT INTO promos (code,discount,minPrice,active,created_at) VALUES ('" + promo.getCode() + "','" + promo.getDiscount() + "','" + promo.getMinPrice() + "','" + promo.getActive() + "',CURRENT_TIMESTAMP);";
                PreparedStatement prest;
                prest = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prest.executeUpdate();
                ResultSet rs = prest.getGeneratedKeys();
                if(rs.next()) {
                    lastId = rs.getInt("id")+1;
                }
            } else {
                lastId = promos.getInt("id")+1;
                return false;
            }
        } catch (Exception e) { }
        return true;
    }

    public static boolean activatePromo(int id)
    {
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE promos SET active=true WHERE id='" + id + "'";
            stmt.executeQuery(sql);
            return true;
        } catch (Exception e) { }
        return false;
    }

    public static boolean deactivatePromo(int id)
    {
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE promos SET active=false WHERE id='" + id + "'";
            stmt.executeQuery(sql);
            return false;
        } catch (Exception e) { }
        return true;
    }

    public static boolean removePromo(int id) throws PromoNotFoundException
    {
        try {
            stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM promos WHERE id='" + id + "'");
            return true;
        } catch (Exception e) { }
        throw new PromoNotFoundException(id);
    }
}
