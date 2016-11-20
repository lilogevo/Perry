package a461lapp.perry;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.TextView;


/**
 * Created by bkool on 11/18/2016.
 */
public class Time extends AppCompatActivity {

    private TimePicker picker;
    private Button displayTime;
    private TextView textview;
    private String hour;
    private String am_pm = " AM";
    private String minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        textview = (TextView) findViewById(R.id.time_activity_text_view);
        picker = (TimePicker) findViewById(R.id.time_activity_time_picker);
        displayTime = (Button) findViewById(R.id.time_activity_confirm_button);

        textview.setText(getCurrentTime());

        displayTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textview.setText(getCurrentTime());
            }

        });


    }

    public String getCurrentTime(){
        int h = picker.getHour();
        if(h >= 12){
            this.am_pm = " PM";             //time is PM and converts the time
            if(h >= 13 && h <24){           //1-11pm
                h-= 12;
            } else {
                h = 12;
            }              //12pm
        } else if(h == 0){
            h = 12;                        //12am
        }

        this.hour = "" + h;
        this.minute = "" + picker.getMinute();


        StringBuilder builder=new StringBuilder();
        builder.append("Current Time: ");
        builder.append(this.hour + "/");
        builder.append(this.minute + "/");
        builder.appent(this.am_pm + "/")
        return builder.toString();
    }

    public String getHour(){
        return hour;
    }

    public String getMinute(){
        return minute;
    }

    public String getAm_pm() {return am_pm;}

}