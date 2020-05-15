package AmandaNurOktaviani.JFood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JFood
{
   public static void main(String[] args)
   {
      Location location = new Location("Jakarta Pusat", "DKI Jakarta", "Lokasi");
      DatabaseSeller.addSeller(new Seller(DatabaseSeller.getLastId()+1, "Amanda", "oktaamanda@gmail.com", "08176744313", location));
      try {
         DatabaseFoodPostgre.addFood(new Food(DatabaseFoodPostgre.getLastId() + 1, "Ramen Enak", DatabaseSeller.getSellerById(1), 50000, FoodCategory.Noodles));
      }
      catch (SellerNotFoundException e){
         System.out.println(e.getMessage());
      }
      try {
         DatabaseFoodPostgre.addFood(new Food(DatabaseFoodPostgre.getLastId() + 1, "Mie Ayam", DatabaseSeller.getSellerById(1), 20000, FoodCategory.Noodles));
      }
      catch (SellerNotFoundException e){
         System.out.println(e.getMessage());
      }
      try {
         DatabasePromoPostgre.addPromo(new Promo(DatabasePromoPostgre.getLastId() + 1, "1234", 5000, 20000, true));
      }
      catch (Exception e){
         System.out.println(e.getMessage());
      }
      SpringApplication.run(JFood.class, args);
   }

}