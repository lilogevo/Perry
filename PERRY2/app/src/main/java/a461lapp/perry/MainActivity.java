package a461lapp.perry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    Button changeActivityExampleButton;
    DBHelper db;
    List<Data> listOfAlarms;
    ListView yourListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listOfAlarms = new ArrayList<>();


        yourListView = (ListView) findViewById(R.id.table);

        db = DBHelper.getInstance(this);
        ArrayList<Data> temp = db.getAllAlarms();
        for (int i = 0; i < temp.size(); i++){
            listOfAlarms.add(temp.get(i));
        }

        ListAdapter customAdapter = new ListAdapter(this, R.layout.itemlistrow, listOfAlarms);
        yourListView.setAdapter(customAdapter);

        changeActivityExampleButton = (Button) findViewById(R.id.createAlarm);
        changeActivityExampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText taskName = (EditText) findViewById(R.id.alarmName);
                Intent i = new Intent(MainActivity.this, Notification.class);
                i.putExtra("alarm", taskName.getText().toString());
                //Integer num = extras.getInt("ex_int");// goes in data.java
                startActivity(i);
            }
        });

        Button updateButton = (Button) findViewById(R.id.updateAlarm);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfAlarms.clear();
                ArrayList<Data> temp = db.getAllAlarms();
                for (int i = 0; i < temp.size(); i++){
                    listOfAlarms.add(temp.get(i));
                    System.out.println(temp.get(i).getName());
                }
               updateAlarm();
            }
        });

    }

    public void updateAlarm(){
        ListAdapter newAdapter = new ListAdapter(this, R.layout.itemlistrow, listOfAlarms);
        yourListView.setAdapter(newAdapter);
    }
}
