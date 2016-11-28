package a461lapp.perry;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

/**
 * Created by bkool on 11/12/2016.
 */

public class Notification extends Activity {

    private Date date;
    private Time time;
    private Calendar calendar;
    private PendingIntent pendingIntent;
    private TextView alarmHeader;
    private String result;
    private Intent alarmIntent;

    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        /* Retrieve a PendingIntent that will perform a broadcast */
        alarmIntent = new Intent(Notification.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(Notification.this, 0, alarmIntent, 0);
        Intent taskIntent = getIntent();
        alarmHeader = (TextView) findViewById(R.id.text);
        result = taskIntent.getStringExtra("alarm");
        alarmHeader.setText(result);
        calendar = Calendar.getInstance();

        findViewById(R.id.setTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = new Time();
                Intent j = new Intent(Notification.this, Time.class);
                j.putExtra("EditingTime", time);
                startActivityForResult(j, 7890);
            }
        });

        findViewById(R.id.setDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = new Date();
                Intent i = new Intent(Notification.this, Date.class);
                i.putExtra("Editing", date);
                startActivityForResult(i, 123456);
            }

        });

        findViewById(R.id.checkBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlarm();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 123456 && resultCode == RESULT_OK && data != null) {
            year = data.getStringExtra("Year");
            month = data.getStringExtra("Month");
            day = data.getStringExtra("Day");

        }

        if ((requestCode == 7890) && (resultCode == RESULT_OK) && (data != null)) {
            hour = data.getStringExtra("Hour");
            minute = data.getStringExtra("Minute");
        }
    }

    private void createAlarm() {
        int interval = 1000 * 60 * 20;

        System.out.println(month + year + day + " " + hour + ":" + minute);
        pendingIntent = PendingIntent.getBroadcast(Notification.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        calendar.set(Calendar.HOUR, Integer.valueOf(hour));
        calendar.set(Calendar.MINUTE, Integer.valueOf(minute));
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(day));
        calendar.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        calendar.set(Calendar.YEAR, Integer.valueOf(year));
        calendar.set(Calendar.SECOND, 0);

        System.out.println("Calender: " + calendar.getTimeInMillis());
        System.out.println("Current: " + System.currentTimeMillis());
        if (calendar.getTimeInMillis() > System.currentTimeMillis())
            System.out.println("In the future");

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, pendingIntent);

    }

    public String getYear(){
        return this.year;
    }

    public String getMonth(){
        return this.month;
    }

    public String getDay(){
        return this.day;
    }

    public String getHour(){
        return this.hour;
    }

    public String getMinute(){
        return this.minute;
    }
}
