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
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();

        Intent ringtone_intent = new Intent(context, RingtoneService.class);
        context.startService(ringtone_intent);
    }
}
