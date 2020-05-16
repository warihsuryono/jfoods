package amanda.nur.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SelesaiPesananActivty extends AppCompatActivity {
    int currentUserId;
    String currentInvoiceId;
    TextView title;
    TextView lblInvoiceId;
    TextView invoiceId;
    TextView lblInvoiceDate;
    TextView invoiceDate;
    TextView lblBillTo;
    TextView lblCustomer;
    TextView customerName;
    TextView lblPhone;
    TextView phone;
    TextView lblLocation;
    TextView location;
    TextView lblOrder;
    GridView gridOrders;
    TextView lblDisc;
    TextView disc;
    TextView lblDelivery;
    TextView delivery;
    TextView lblTotal;
    TextView total;
    Button btnBatal;
    Button btnSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        title = findViewById(R.id.title);
        lblInvoiceId = findViewById(R.id.lblInvoiceId);
        invoiceId = findViewById(R.id.invoiceId);
        lblInvoiceDate = findViewById(R.id.lblInvoiceDate);
        invoiceDate = findViewById(R.id.invoiceDate);
        lblBillTo = findViewById(R.id.lblBillTo);
        lblCustomer = findViewById(R.id.lblCustomer);
        customerName = findViewById(R.id.customerName);
        lblPhone = findViewById(R.id.lblPhone);
        phone = findViewById(R.id.phone);
        lblLocation = findViewById(R.id.lblLocation);
        location = findViewById(R.id.location);
        lblOrder = findViewById(R.id.lblOrder);
        gridOrders = findViewById(R.id.orders);
        lblDisc = findViewById(R.id.lblDisc);
        disc = findViewById(R.id.dics);
        lblDelivery = findViewById(R.id.lblDelivery);
        delivery = findViewById(R.id.delivery);
        lblTotal = findViewById(R.id.lblTotal);
        total = findViewById(R.id.total);
        btnBatal = findViewById(R.id.btnBatal);
        btnSelesai = findViewById(R.id.btnSelesai);

        title.setVisibility(View.GONE);
        lblInvoiceId.setVisibility(View.GONE);
        invoiceId.setVisibility(View.GONE);
        lblInvoiceDate.setVisibility(View.GONE);
        invoiceDate.setVisibility(View.GONE);
        lblBillTo.setVisibility(View.GONE);
        lblCustomer.setVisibility(View.GONE);
        customerName.setVisibility(View.GONE);
        lblPhone.setVisibility(View.GONE);
        phone.setVisibility(View.GONE);
        lblLocation.setVisibility(View.GONE);
        location.setVisibility(View.GONE);
        lblOrder.setVisibility(View.GONE);
        gridOrders.setVisibility(View.GONE);
        lblDisc.setVisibility(View.GONE);
        disc.setVisibility(View.GONE);
        lblDelivery.setVisibility(View.GONE);
        delivery.setVisibility(View.GONE);
        lblTotal.setVisibility(View.GONE);
        total.setVisibility(View.GONE);
        btnBatal.setVisibility(View.GONE);
        btnSelesai.setVisibility(View.GONE);
        Intent intent= getIntent();
        Bundle b = intent.getExtras();
        if(b!=null) {
            currentUserId = (int) b.get("customerId");
            fetchPesanan(currentUserId);
        }

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesananSelesai(currentInvoiceId);
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesananBatal(currentInvoiceId);
            }
        });
    }

    protected void pesananSelesai(final String invoiceID) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true")){
                    Toast.makeText(SelesaiPesananActivty.this,"Pesanan berhasil diselesaikan", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SelesaiPesananActivty.this,"Pesanan gagal diselesaikan", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(SelesaiPesananActivty.this,MainActivity.class);
                intent.putExtra("customerId", currentUserId);
                startActivity(intent);
            }
        };

        PesananSelesaiRequest pesananSelesaiRequest = new PesananSelesaiRequest(invoiceID,responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivty.this);
        queue.add(pesananSelesaiRequest);
    }

    protected void pesananBatal(final String invoiceID) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true")){
                    Toast.makeText(SelesaiPesananActivty.this,"Pesanan berhasil dibatalkan", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SelesaiPesananActivty.this,"Pesanan gagal dibatalkan", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(SelesaiPesananActivty.this,MainActivity.class);
                intent.putExtra("customerId", currentUserId);
                startActivity(intent);
            }
        };

        PesananBatalRequest pesananBatalRequest = new PesananBatalRequest(invoiceID,responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivty.this);
        queue.add(pesananBatalRequest);
    }

    protected void fetchPesanan(final int customerId) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject invoice = new JSONObject(response.substring(1,response.length()-1));
                    if( invoice != null && invoice.getString("invoiceStatus").equals("Ongoing")){
                        title.setVisibility(View.VISIBLE);
                        lblInvoiceId.setVisibility(View.VISIBLE);
                        invoiceId.setVisibility(View.VISIBLE);
                        lblInvoiceDate.setVisibility(View.VISIBLE);
                        invoiceDate.setVisibility(View.VISIBLE);
                        lblBillTo.setVisibility(View.VISIBLE);
                        lblCustomer.setVisibility(View.VISIBLE);
                        customerName.setVisibility(View.VISIBLE);
                        lblPhone.setVisibility(View.VISIBLE);
                        phone.setVisibility(View.VISIBLE);
                        lblLocation.setVisibility(View.VISIBLE);
                        location.setVisibility(View.VISIBLE);
                        lblOrder.setVisibility(View.VISIBLE);
                        gridOrders.setVisibility(View.VISIBLE);
                        lblDisc.setVisibility(View.VISIBLE);
                        disc.setVisibility(View.VISIBLE);
                        lblDelivery.setVisibility(View.VISIBLE);
                        delivery.setVisibility(View.VISIBLE);
                        lblTotal.setVisibility(View.VISIBLE);
                        total.setVisibility(View.VISIBLE);
                        btnBatal.setVisibility(View.VISIBLE);
                        btnSelesai.setVisibility(View.VISIBLE);
                        currentInvoiceId = invoice.getString("id");
                        invoiceId.setText(currentInvoiceId);
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        invoiceDate.setText(df.format(Calendar.getInstance().getTime()));
                        JSONObject customer = new JSONObject(invoice.getString("customer"));
                        customerName.setText(customer.getString("name"));
                        phone.setText("");
                        location.setText("");
                        JSONArray foods = new JSONArray(invoice.getString("foods"));
                        String[] orders = new String[foods.length()*2];
                        int x = 0;
                        int price = 0;
                        for (int i = 0 ; i < foods.length(); i++) {
                            JSONObject food = foods.getJSONObject(i);
                            orders[x] = food.getString("name");
                            x++;
                            orders[x] = food.getString("price");
                            x++;
                            price += food.getInt("price");
                        }
                        OrdersAdapter ordersAdapter = new OrdersAdapter(SelesaiPesananActivty.this, orders);
                        int discPromo = price -  invoice.getInt("totalPrice");
                        disc.setText(String.valueOf(discPromo));
                        total.setText(invoice.getString("totalPrice"));
                        gridOrders.setAdapter(ordersAdapter);
                        delivery.setText("0");
                    } else {
                        Intent intent = new Intent(SelesaiPesananActivty.this,MainActivity.class);
                        intent.putExtra("customerId", customerId);
                        startActivity(intent);
                    }
                }
                catch (JSONException e) {
                    Intent intent = new Intent(SelesaiPesananActivty.this,MainActivity.class);
                    intent.putExtra("customerId", customerId);
                    startActivity(intent);
                }
            }
        };

        PesananFetchRequest pesananFetchRequest = new PesananFetchRequest(currentUserId,responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivty.this);
        queue.add(pesananFetchRequest);
    }
}
