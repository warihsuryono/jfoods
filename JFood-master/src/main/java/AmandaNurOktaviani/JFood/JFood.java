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
         DatabaseFood.addFood(new Food(DatabaseFood.getLastId() + 1, "Ramen Enak", DatabaseSeller.getSellerById(1), 50000, FoodCategory.Noodles));
      }
      catch (SellerNotFoundException e){
         System.out.println(e.getMessage());
      }
      try {
         DatabaseFood.addFood(new Food(DatabaseFood.getLastId() + 1, "Mie Ayam", DatabaseSeller.getSellerById(1), 20000, FoodCategory.Noodles));
      }
      catch (SellerNotFoundException e){
         System.out.println(e.getMessage());
      }
      SpringApplication.run(JFood.class, args);
   }

}