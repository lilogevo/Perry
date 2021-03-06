package a461lapp.perry;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class ListAlarmsActivity extends Activity {
    DBHelper db;
    ListView alarmList;
    ArrayList<Data> arrayList;
    ArrayAdapter<Data> arrayAdapter;
    ArrayList<String> myIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_alarms);

        alarmList = (ListView) findViewById(R.id.alarm_list);
        setTitle("Alarms");
        db = DBHelper.getInstance(this);
        arrayList = db.getAllAlarms();
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,convertNotificationToString(arrayList));

        myIDs = db.getAllIDs();

        alarmList.setAdapter(arrayAdapter);
        alarmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // Integer idToSearch = Integer.parseInt(myIDs.get(position));
               // db.deleteAlarm(Integer.parseInt(myIDs.get(position)));
            }
        });
    }

    ArrayList<String> convertNotificationToString(ArrayList<Data> notifArrayList){
        ArrayList<String> arrayList = new ArrayList<>();
        for(Data notification : notifArrayList){
            StringBuilder builder = new StringBuilder();
            builder.append(notification.getMonth());
            builder.append("/");
            builder.append(notification.getDay());
            builder.append("/");
            builder.append(notification.getYear());
            builder.append(" ");
            Boolean isAM = true;
            Integer hour = Integer.parseInt(notification.getHour());
            if(hour >= 12){ hour -= 12; isAM = false; }
            if(hour == 0) { hour = 12; }
            builder.append(hour);
            builder.append(":");
            builder.append(notification.getMinute());
            if(isAM){
                builder.append("AM");
            }else {
                builder.append("PM");
            }
            arrayList.add(builder.toString());
        }
        return arrayList;
    }

     /*@Override
   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_list_alarms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /*if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
