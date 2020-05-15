package amanda.nur.jfood_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class BuatPesananActivity extends AppCompatActivity {
    int currentUserId;
    int id_food;
    String foodName;
    String foodCategory;
    double foodPrice;
    int promoCode;
    String selectedPayment;
    TextView textCode = null;
    TextView food_name = null;
    TextView food_category = null;
    TextView food_price = null;
    TextView total_price = null;
    TextView pesanan = null;
    EditText promo_code = null;
    RadioGroup radioGroup = null;
    RadioButton cash = null;
    RadioButton cashless = null;
    Button hitung = null;
    Button pesan = null;
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
        textCode = findViewById(R.id.textCode);
        food_name = findViewById(R.id.food_name);
        food_category = findViewById(R.id.food_category);
        food_price = findViewById(R.id.food_price);
        total_price = findViewById(R.id.total_price);
        pesanan = findViewById(R.id.pesanan);
        promo_code = findViewById(R.id.promo_code);
        radioGroup = findViewById(R.id.radioGroup);
        cash = findViewById(R.id.cash);
        cashless = findViewById(R.id.cashless);
        hitung = findViewById(R.id.hitung);
        pesan = findViewById(R.id.pesan);
        pesan.setVisibility(View.GONE);
        textCode.setVisibility(View.GONE);
        promo_code.setVisibility(View.GONE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.cashless){
                    textCode.setVisibility(View.VISIBLE);
                    promo_code.setVisibility(View.VISIBLE);
                    selectedPayment = "cashless";
                } else {
                    textCode.setVisibility(View.GONE);
                    promo_code.setVisibility(View.GONE);
                    selectedPayment = "cash";
                }
            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if( jsonObject != null){
                                JSONObject promo = jsonObject;
                                Double promoDisc = promo.getDouble("discount");
                                Double promoMinPrice = promo.getDouble("minPrice");
                                Boolean promoActive = promo.getBoolean("active");
                                if(promoActive && foodPrice >= promoMinPrice) {
                                    total_price.setText(String.valueOf(foodPrice - promoDisc));
                                } else {
                                    total_price.setText(String.valueOf(foodPrice));
                                }
                            }
                        }
                        catch (JSONException e)
                        {
                            total_price.setText(String.valueOf(foodPrice));
                            Toast.makeText(BuatPesananActivity.this,"Invalid promo code", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error Occurred", error);
                    }
                };

                if(selectedPayment.equals("cash")){
                    total_price.setText(String.valueOf(foodPrice));
                }
                if(selectedPayment.equals("cashless")){
                    if(!promo_code.getText().toString().equals("")){
                        promoCode = Integer.parseInt(promo_code.getText().toString());
                        CheckPromoRequest checkPromoRequest = new CheckPromoRequest(promoCode, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                        queue.add(checkPromoRequest);
                    } else {
                        total_price.setText(String.valueOf(foodPrice));
                    }
                }
                hitung.setVisibility(View.GONE);
                pesan.setVisibility(View.VISIBLE);
            }
        });

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if( jsonObject != null){
                                JSONObject promo = jsonObject;
                                Toast.makeText(BuatPesananActivity.this,"Pesanan berhasil ditambahkan", Toast.LENGTH_LONG).show();

                            }
                        }
                        catch (JSONException e)
                        {
                            total_price.setText(String.valueOf(foodPrice));
                            Toast.makeText(BuatPesananActivity.this,"Pesanan Gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Error Occurred", error);
                    }
                };

                BuatPesananRequest buatPesananRequest = null;
                if(selectedPayment == "cash")
                    buatPesananRequest = new BuatPesananRequest("createCashInvoice", String.valueOf(id_food),String.valueOf(currentUserId),String.valueOf(promoCode),"0", responseListener);

                if(selectedPayment == "cashless")
                    buatPesananRequest = new BuatPesananRequest("createCashlessInvoice", String.valueOf(id_food),String.valueOf(currentUserId),String.valueOf(promoCode),"0", responseListener);

                RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                queue.add(buatPesananRequest);
            }
        });

    }

    protected void getFood(int id_food) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if( jsonObject != null){
                        JSONObject food = jsonObject;
                        foodName = food.getString("name");
                        foodCategory = food.getString("category");
                        foodPrice = food.getDouble("price");
                        food_name.setText(foodName);
                        food_category.setText(foodCategory);
                        food_price.setText(String.valueOf(foodPrice));
                        total_price.setText("0");
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
