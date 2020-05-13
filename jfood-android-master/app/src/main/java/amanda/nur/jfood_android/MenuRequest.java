package amanda.nur.jfood_android;

import android.view.Menu;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MenuRequest extends StringRequest {
    private static final String URL = LoginActivity.API_ADDR + "food";
    private Map<String, String> params;

    public MenuRequest(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);
        params = new HashMap<>();


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
