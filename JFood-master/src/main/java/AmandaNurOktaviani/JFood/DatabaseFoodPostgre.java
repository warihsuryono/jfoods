package AmandaNurOktaviani.JFood;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Write a description of class DatabaseFood here.
 *
 * @author (Amanda)
 * @version (27/02/2020)
 */
public class DatabaseFoodPostgre
{
    private static Connection conn = DatabaseConnectionPostgre.connection();
    private static Statement stmt = null;
    // instance variables
    private static int lastId=0;

    public static ArrayList<Food> getFoodDatabase()
    {
        ArrayList<Food> list = new ArrayList<Food>();
        try {
            stmt = conn.createStatement();
            ResultSet foods = stmt.executeQuery("SELECT * FROM foods");
            while(foods.next()) {
                Seller seller = DatabaseSeller.getSellerById(foods.getInt("seller_id"));
                Food food = new Food(foods.getInt("id"), foods.getString("name"),seller , foods.getInt("price"), FoodCategory.valueOf(foods.getString("category")));
                list.add(food);
            }
        } catch (Exception e) { }
        return list;
    }

    public static int getLastId()
    {
        return lastId;
    }

    public static int getLastFoodId()
    {
        try {
            stmt = conn.createStatement();
            ResultSet foods = stmt.executeQuery("SELECT id FROM foods ORDER BY id DESC LIMIT 1");
            if(foods.next()) {
                return foods.getInt("id");
            }
        } catch (Exception e) { }
        return 0;
    }

    public static Food getFoodById(int id) throws FoodNotFoundException
    {
        try {
            stmt = conn.createStatement();
            ResultSet foods = stmt.executeQuery("SELECT * FROM foods WHERE id='" + id + "'");
            if(foods.next()) {
                Seller seller = DatabaseSeller.getSellerById(foods.getInt("seller_id"));
                return new Food(id, foods.getString("name"),seller , foods.getInt("price"), FoodCategory.valueOf(foods.getString("category")));
            } else {
                return null;
            }

        } catch (Exception e) { }
        throw new FoodNotFoundException(id);
    }

    public static ArrayList<Food> getFoodBySeller(int sellerId)
    {
        ArrayList<Food> list = new ArrayList<Food>();
        try {
            stmt = conn.createStatement();
            ResultSet foods = stmt.executeQuery("SELECT * FROM foods WHERE seller_id='" + sellerId + "'");
            while(foods.next()) {
                Seller seller = DatabaseSeller.getSellerById(sellerId);
                Food food = new Food(foods.getInt("id"), foods.getString("name"),seller , foods.getInt("price"), FoodCategory.valueOf(foods.getString("category")));
                list.add(food);
            }
        } catch (Exception e) { }
        return list;
    }

    public static ArrayList<Food> getFoodByCategory(FoodCategory category)
    {
        ArrayList<Food> list = new ArrayList<Food>();
        try {
            stmt = conn.createStatement();
            ResultSet foods = stmt.executeQuery("SELECT * FROM foods WHERE category='" + category.toString() + "'");
            while(foods.next()) {
                Seller seller = DatabaseSeller.getSellerById(foods.getInt("seller_id"));
                Food food = new Food(foods.getInt("id"), foods.getString("name"),seller , foods.getInt("price"), category);
                list.add(food);
            }
        } catch (Exception e) { }
        return list;
    }

    public static boolean addFood(Food food)
    {
        try {
            stmt = conn.createStatement();
            String sql = "SELECT id FROM foods  WHERE seller_id='"+ food.getSeller().getId() +"' AND name = '" + food.getName() + "'";
            ResultSet foods = stmt.executeQuery(sql);
            if(!foods.next()) { //cek apakah email sudah ada di table customers, jika belum insert sellers
                sql = "INSERT INTO foods (seller_id,name,price,category,created_at) VALUES ('" + food.getSeller().getId() + "','" + food.getName() + "','" + food.getPrice() + "','" + food.getCategory().toString() + "',CURRENT_TIMESTAMP);";
                PreparedStatement prest;
                prest = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prest.executeUpdate();
                ResultSet rs = prest.getGeneratedKeys();
                if(rs.next()) {
                    lastId = rs.getInt("id")+1;
                }
            } else {
                lastId = foods.getInt("id")+1;
                return false;
            }
        } catch (Exception e) { }
        return true;
    }

    public static boolean removeFood(int id) throws FoodNotFoundException
    {
        try {
            stmt = conn.createStatement();
            stmt.executeQuery("DELETE FROM foods WHERE id='" + id + "'");
            return true;
        } catch (Exception e) { }
        throw new FoodNotFoundException(id);
    }
}
