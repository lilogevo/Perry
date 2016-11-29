package a461lapp.perry;

/**
 * Created by aftabhadimohd on 11/28/16.
 */

import android.content.*;
import android.view.*;
import java.util.*;
import android.widget.*;


public class ListAdapter extends ArrayAdapter<Data> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<Data> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Data p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.id);
            TextView tt2 = (TextView) v.findViewById(R.id.categoryId);
            TextView tt3 = (TextView) v.findViewById(R.id.description);

            if (tt1 != null) {
                tt1.setText(p.getYear());
            }

            if (tt2 != null) {
                tt2.setText(p.getHour());
            }

            if (tt3 != null) {
                tt3.setText(p.getMinute());
            }
        }

        return v;
    }

}