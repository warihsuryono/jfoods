package amanda.nur.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BuatPesananRequest extends StringRequest {
    private static final String URL = LoginActivity.API_ADDR + "invoice";
    private Map<String, String> params;

    public BuatPesananRequest(String selectedPayment,String foodId,String customerId,String promoCode,String deliveryFee, Response.Listener<String> listener) {
        super(Request.Method.POST, URL+"/"+selectedPayment, listener, null);
        params = new HashMap<>();
        params.put("foodId",foodId);
        params.put("customerId",customerId);
        params.put("deliveryFee",deliveryFee);
        params.put("promoCode",promoCode);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
