package a461lapp.perry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button changeActivityExampleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeActivityExampleButton = (Button) findViewById(R.id.ex);
        changeActivityExampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Notification.class);
                Bundle extras = new Bundle();
                extras.putInt("ex_int", 42);
                i.putExtras(extras);
                //Integer num = extras.getInt("ex_int");// goes in data.java
                startActivity(i);
            }
        });
    }
}
