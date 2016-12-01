package a461lapp.perry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by bkool on 11/12/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("received");
        Intent ringtone_intent = new Intent(context, RingtoneService.class);
        if (intent.getStringExtra("extra").equals("nothing")) {
            ringtone_intent.putExtra("extra", "nothing");
            Toast.makeText(context, "Alarm is ringing", Toast.LENGTH_SHORT).show();
        } else {
            ringtone_intent.putExtra("extra", "alarm off");
            Toast.makeText(context, "Alarm is off", Toast.LENGTH_SHORT).show();
        }
        context.startService(ringtone_intent);
    }
}
