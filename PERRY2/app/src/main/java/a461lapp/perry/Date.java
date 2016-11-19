package a461lapp.perry;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Created by bkool on 11/12/2016.
 */
public class Date extends AppCompatActivity {

    private DatePicker picker;
    private Button displayDate;
    private TextView textview;
    private String year;
    private String month;
    private String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        textview = (TextView) findViewById(R.id.date_activity_text_view);
        picker = (DatePicker) findViewById(R.id.date_activity_date_picker);
        displayDate = (Button) findViewById(R.id.date_activity_confirm_button);

        textview.setText(getCurrentDate());

        displayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textview.setText(getCurrentDate());
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

