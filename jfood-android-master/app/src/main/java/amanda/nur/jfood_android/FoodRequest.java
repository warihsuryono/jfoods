package amanda.nur.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FoodRequest extends StringRequest {
    private static final String URL = LoginActivity.API_ADDR + "food";
    private Map<String, String> params;

    public FoodRequest(int foodId,Response.Listener<String> listener) {
        super(Method.GET, URL+"/"+foodId, listener, null);
        params = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
