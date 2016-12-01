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
    boolean startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");
        assert state != null;
        if(state.equals("alarm off")){
            this.startId = false;
        }
        else{
            this.startId = true;
        }

        if(this.isRunning && startId == 0){
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.startId = false;
        }

        else if(!this.isRunning && startId == 0){
            this.isRunning = false;
            this.startId = false;
        }

        else{
            media_song = MediaPlayer.create(this, R.raw.alarm);
            media_song.start();
            this.isRunning = true;
            this.startId = false;
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Toast.makeText(this, "onDestroy Called", Toast.LENGTH_SHORT).show();
    }
}
