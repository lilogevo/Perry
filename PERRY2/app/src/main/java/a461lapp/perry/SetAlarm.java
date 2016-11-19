package a461lapp.perry;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by bkool on 11/12/2016.
 */
public class SetAlarm extends Activity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public SetAlarm(){

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setTheAlarm() {
        Intent alarmIntent = new Intent(SetAlarm.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(SetAlarm.this, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        /* Set the alarm to the date specified by user */


        /* Repeating on every 20 minutes interval */
    }
}
