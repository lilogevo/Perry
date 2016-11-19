package a461lapp.perry;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.Calendar;

/**
 * Created by bkool on 11/12/2016.
 */
public class Notification extends Activity {

    private SetAlarm alarm;
    private Date date;
    private Time time;
    private PendingIntent pendingIntent;
    private Calendar calendar;

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
                setAlarmDate();
            }
        });

        findViewById(R.id.checkBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlarm();
            }
        });
    }

    private void setAlarmTime() {

    }

    private void setAlarmDate() {
        Date date = new Date();
        int year = Integer.valueOf(date.getYear());
        int month = Integer.valueOf(date.getMonth());
        int day = Integer.valueOf(date.getDay());
        calendar.set(year, month, day);
    }

    private void createAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        /* Set the alarm to start at time and date specified by user */


        /* Repeating on every n minutes interval */

    }

}
