package amanda.nur.jfood_android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OrdersAdapter extends BaseAdapter {
    private final Context mContext;
    private final String[] orders;

    public OrdersAdapter(Context context, String[] orders) {
        this.mContext = context;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dummyTextView = new TextView(mContext);
        dummyTextView.setText(orders[position]);
        return dummyTextView;
    }
}
