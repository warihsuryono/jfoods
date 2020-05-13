package AmandaNurOktaviani.JFood;
import java.util.ArrayList;
/**
 * Write a description of class DatabaseFood here.
 *
 * @author (Amanda)
 * @version (27/02/2020)
 */
public class DatabaseFood
{
    // instance variables
    private static final ArrayList<Food> FOOD_DATABASE = new ArrayList<Food>();
    private static int lastId = 0;

    public static ArrayList<Food> getFoodDatabase()
    {
        return FOOD_DATABASE;
    }

    public static int getLastId()
    {
        return lastId;
    }

    public static Food getFoodById(int id) throws FoodNotFoundException
    {
        for(Food food:FOOD_DATABASE)
        {
            if(food.getId()==id)
            {
                return food;
            }
        }
        throw new FoodNotFoundException(id);
    }

    public static ArrayList<Food> getFoodBySeller(int sellerId)
    {
        ArrayList<Food> list = new ArrayList<Food>();
        for (Food food:FOOD_DATABASE)
        {
            if(food.getSeller().getId()==sellerId)
            {
                list.add(food);
            }
        }
        return list;
    }

    public static ArrayList<Food> getFoodByCategory(FoodCategory category)
    {
        ArrayList<Food> list = new ArrayList<Food>();
        for (Food food:FOOD_DATABASE)
        {
            if(food.getCategory().equals(category))
            {
                list.add(food);
            }
        }
        return list;
    }

    public static boolean addFood(Food food)
    {
        FOOD_DATABASE.add(food);
        lastId=food.getId()+1;
        return true;
    }

    public static boolean removeFood(int id) throws FoodNotFoundException
    {
        for(int i=0; i<FOOD_DATABASE.size(); i++)
        {
            Food food = FOOD_DATABASE.get(i);
            if(food.getId()==id)
            {
                FOOD_DATABASE.remove(i);
                return true;
            }
        }
        throw new FoodNotFoundException(id);
    }
}
