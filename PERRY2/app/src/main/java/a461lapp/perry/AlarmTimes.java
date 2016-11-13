package a461lapp.perry;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

/**
 * Created by bkool on 11/12/2016.
 */
public class AlarmTimes extends Activity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public AlarmTimes(){

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Intent alarmIntent = new Intent(AlarmTimes.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(AlarmTimes.this, 0, alarmIntent, 0);


    }
}
