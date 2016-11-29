package a461lapp.perry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    Button changeActivityExampleButton;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
    }
}
