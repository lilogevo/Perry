package a461lapp.perry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.*;
import android.widget.AdapterView.*;
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
                startActivityForResult(i, 1010);
            }
        });

        yourListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Data data = (Data) yourListView.getItemAtPosition(position);
                System.out.println("Click Listener");
                System.out.println(data.getMonth() + data.getDay() + data.getYear());

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1010 && resultCode == RESULT_OK) {
            updateAlarm();
            System.out.println("Working");
        }

    }

    public void updateAlarm(){
        ArrayList<Data> temp = db.getAllAlarms();
        listOfAlarms.clear();
        for (int i = 0; i < temp.size(); i++){
            listOfAlarms.add(temp.get(i));
        }
        ListAdapter newAdapter = new ListAdapter(this, R.layout.itemlistrow, listOfAlarms);
        yourListView.setAdapter(newAdapter);
    }
}
