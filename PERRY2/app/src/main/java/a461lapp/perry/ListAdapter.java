package a461lapp.perry;

/**
 * Created by aftabhadimohd on 11/28/16.
 */

import android.content.*;
import android.view.*;
import java.util.*;
import android.widget.*;
import android.app.*;
import android.widget.AdapterView.*;



public class ListAdapter extends ArrayAdapter<Data>  {

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

        final Data p = getItem(position);

        if (p != null) {
            TextView name = (TextView) v.findViewById(R.id.alarm_name);
            TextView date = (TextView) v.findViewById(R.id.date);


            if (name != null){
                name.setText(p.getName());
            }

            if (date != null ) {
                String alarmDate = p.getMonth() + "/" + p.getDay() + "/" + p.getYear();
                String alarmTime = p.getHour() + ":" + p.getMinute();
                date.setText(alarmDate + "            " + alarmTime);
            }

        }

//        ImageButton info = (ImageButton)v.findViewById(R.id.infoButton);
//        info.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent( getContext(), Notification.class);
//                i.putExtra("alarm", p);
//                //Integer num = extras.getInt("ex_int");// goes in data.java
//                getContext().startActivity(i);
//            }
//        });


        return v;
    }


}