package amanda.nur.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PesananSelesaiRequest extends StringRequest {
    private static final String URL = LoginActivity.API_ADDR + "invoice/invoiceStatus";
    private Map<String, String> params;

    public PesananSelesaiRequest(String invoiceId, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("ID",invoiceId);
        params.put("Invoice","Finished");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
