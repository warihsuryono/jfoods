package amanda.nur.jfood_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuatPesananActivity extends AppCompatActivity {
    int currentUserId;
    int id_food;
    String foodName;
    String foodCategory;
    double foodPrice;
    int promoCode;
    String selectedPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);

        Intent intent= getIntent();
        Bundle b = intent.getExtras();
        if(b!=null) {
            currentUserId = (int) b.get("customerId");
            id_food = (int) b.get("foodId");
            getFood(id_food);
        }

    }

    protected void getFood(int id_food) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if( jsonObject != null){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(BuatPesananActivity.this);

                        JSONObject food = jsonObject;
                        JSONObject seller = food.getJSONObject("seller");
                        JSONObject location = seller.getJSONObject("location");

                        Location newLocation = new Location(
                                location.getString("province"),
                                location.getString("description"),
                                location.getString("city")
                        );

                        Seller newSeller = new Seller(
                                seller.getInt("id"),
                                seller.getString("name"),
                                seller.getString("email"),
                                seller.getString("phoneNumber"),
                                newLocation
                        );

                        Food selectedFood = new Food(food.getInt("id"), food.getString("name"), newSeller, food.getInt("price"), food.getString("category"));
                    }
                }
                catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuatPesananActivity.this);
                    builder.setMessage("Load Data Failed.").create().show();
                }
            }
        };

        FoodRequest foodRequest = new FoodRequest(id_food,responseListener);
        RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
        queue.add(foodRequest);
    }
}
