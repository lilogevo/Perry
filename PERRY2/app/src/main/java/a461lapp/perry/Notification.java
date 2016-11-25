package a461lapp.perry;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Calendar;

/**
 * Created by bkool on 11/12/2016.
 */

public class Notification extends Activity {

    private Date date;
    private Time time;
    private PendingIntent pendingIntent;
    private Calendar calendar;
    private String year;
    private String month;
    private String day;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(Notification.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(Notification.this, 0, alarmIntent, 0);

        findViewById(R.id.setTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarmTime();
            }
        });

        findViewById(R.id.setDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = new Date();
                calendar = Calendar.getInstance();
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

    }


    public void setAlarmTime() {
        time = new Time();
    }

    private void createAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 57);
        //calendar.set(Integer.valueOf(year),Integer.valueOf(month), Integer.valueOf(day), 17, 51);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
               interval, pendingIntent);

    }

}
