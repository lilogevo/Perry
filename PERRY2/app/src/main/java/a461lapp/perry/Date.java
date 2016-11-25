package a461lapp.perry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by bkool on 11/12/2016.
 */
public class Date extends AppCompatActivity implements Serializable {

    private DatePicker picker;
    private Button confirm_date_button;
    private TextView textview;
    private String year;
    private String month;
    private String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        textview = (TextView) findViewById(R.id.text_view);
        picker = (DatePicker) findViewById(R.id.date_picker);
        confirm_date_button = (Button) findViewById(R.id.confirm_date);


        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText(getCurrentDate());

            }
        });

        confirm_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText(getCurrentDate());
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Year", getYear());
                resultIntent.putExtra("Month", getMonth());
                resultIntent.putExtra("Day", getDay());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });


    }

    public String getCurrentDate(){
        this.month = "" + (picker.getMonth() + 1);
        this.day = "" + picker.getDayOfMonth();
        this.year = "" + picker.getYear();


        StringBuilder builder=new StringBuilder();
        builder.append("Current Date: ");
        builder.append(this.month + "/");//month is 0 based
        builder.append(this.day + "/");
        builder.append(this.year);
        return builder.toString();
    }

    public String getYear(){
        return year;
    }

    public String getMonth(){
        return month;
    }

    public String getDay(){
        return day;
    }


}

