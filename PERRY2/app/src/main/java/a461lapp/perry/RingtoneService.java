package a461lapp.perry;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import android.media.MediaPlayer;

/**
 * Created by Royce on 11/26/2016.
 */
public class RingtoneService extends Service {

    MediaPlayer media_song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        media_song = MediaPlayer.create(this, R.raw.alarm);
        media_song.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Toast.makeText(this, "onDestroy Called", Toast.LENGTH_SHORT).show();
    }
}
