package AmandaNurOktaviani.JFood.controller;

import AmandaNurOktaviani.JFood.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/food")
@RestController

public class FoodController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ArrayList<Food> getAllFood()
    {
        ArrayList<Food> foods = new ArrayList<>();
        foods = DatabaseFoodPostgre.getFoodDatabase();
        return foods;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Food getFoodById(@PathVariable int id) {
        Food foods = null;
        try {
            foods = DatabaseFoodPostgre.getFoodById(id);
        } catch (FoodNotFoundException e) {
            e.getMessage();
            return null;
        }
        return foods;
    }

    @RequestMapping(value = "/seller/{sellerId}", method = RequestMethod.GET)
    public ArrayList<Food> getFoodBySeller(@PathVariable int sellerId) {
        ArrayList<Food> foods = new ArrayList<>();
        foods = DatabaseFoodPostgre.getFoodBySeller(sellerId);
        return foods;
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    public ArrayList<Food> getFoodByCategory(@PathVariable FoodCategory category) {
        ArrayList<Food> foods = new ArrayList<>();
        foods = DatabaseFoodPostgre.getFoodByCategory(category);
        return foods;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Food addFood(@RequestParam(value = "name") String name,
                        @RequestParam(value = "price") int price,
                        @RequestParam(value = "category") FoodCategory category,
                        @RequestParam(value = "sellerId") int sellerId)
    {
        Food food = null;
        try
        {
            food = new Food(DatabaseFoodPostgre.getLastId()+1,name,DatabaseSeller.getSellerById(sellerId),price,category);
            DatabaseFoodPostgre.addFood(food);
        }
        catch (SellerNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        return food;
    }
}