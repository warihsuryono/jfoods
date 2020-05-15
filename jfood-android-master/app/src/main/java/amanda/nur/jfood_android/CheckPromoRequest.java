package amanda.nur.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CheckPromoRequest extends StringRequest {
    private static final String URL = LoginActivity.API_ADDR + "promo";
    private Map<String, String> params;

    public CheckPromoRequest(int promoCode, Response.Listener<String> listener) {
        super(Request.Method.GET, URL+"/"+promoCode, listener, null);
        params = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
