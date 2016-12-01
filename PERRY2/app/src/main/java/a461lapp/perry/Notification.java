package a461lapp.perry;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
//import android.widget.Button;
import android.widget.ImageButton;
import android.widget.*;
import android.widget.TextView;
import java.util.Calendar;
import java.util.*;


/**
 * Created by bkool on 11/12/2016.
 */

public class Notification extends Activity {

    private Date date;
    private Time time;
    private Calendar calendar;
    private PendingIntent pendingIntent;
    private TextView alarmHeader;
    private TextView timeHeader;
    private TextView dateHeader;
    private CheckBox check;
    private String taskResult;
    private String timeResult;
    private String dateResult;
    private Intent alarmIntent;
    private Intent taskIntent;
    private long id;

    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private String am_pm;
    private Data data;

    DBHelper db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        db = DBHelper.getInstance(this);
        id = (int) System.currentTimeMillis();
        System.out.println("ID created: " + id);

        alarmHeader = (TextView) findViewById(R.id.text);
        timeHeader = (TextView) findViewById(R.id.changedTime);
        dateHeader = (TextView) findViewById(R.id.changedDate);

        taskIntent = getIntent();
        taskResult = taskIntent.getStringExtra("alarm");
        data = (Data) taskIntent.getSerializableExtra("data");

        /* Retrieve a PendingIntent that will perform a broadcast */
        alarmIntent = new Intent(Notification.this, AlarmReceiver.class);
        alarmIntent.putExtra("extra", "nothing");
        alarmIntent.putExtra("name", taskResult);
        pendingIntent = PendingIntent.getBroadcast(Notification.this, (int) id, alarmIntent, 0);



        if (data != null){
            alarmHeader.setText(data.getName());
            timeHeader.setText("Time Set: " + data.getHour() + ":" +  data.getMinute() + data.getAm_pm());
            dateHeader.setText("Date Set: " + data.getMonth()+ "/" + data.getDay() + "/" + data.getYear());
            this.id = Integer.valueOf(data.getID());
            this.year = data.getYear();
            this.month = data.getMonth();
            this.day = data.getDay();
            this.hour = data.getHour();
            this.minute = data.getMinute();
            this.taskResult = data.getName();
            this.am_pm = data.getAm_pm();
            pendingIntent = PendingIntent.getBroadcast(Notification.this, (int) id, alarmIntent, 0);
            db.deleteAlarm(data.getID());
        }

        else {
            alarmHeader.setText(taskResult);
        }

        calendar = Calendar.getInstance();



        ImageButton deleteButton = (ImageButton) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               db.deleteAlarm(Integer.toString((int) id));
               AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
               manager.cancel(pendingIntent);
               Toast.makeText(getApplicationContext(), "Notification Deleted", Toast.LENGTH_SHORT).show();
               Intent resultIntent = new Intent(Notification.this, MainActivity.class);
               setResult(Activity.RESULT_OK, resultIntent);
               finish();
            }
        });

        ImageButton stopButton = (ImageButton) findViewById(R.id.StopButton);
        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alarmIntent.putExtra("extra", "alarm off");
                db.insertAlarm(Integer.toString((int) id), taskResult, month, day, year, hour, minute, am_pm);
                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                manager.cancel(pendingIntent);
                sendBroadcast(alarmIntent); //stop ringtone
                Intent resultIntent = new Intent(Notification.this, MainActivity.class);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

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
        check = (CheckBox) findViewById(R.id.checkBox);
        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (check.isChecked() && year != null && hour != null) {
                    System.out.println(taskResult);
                    if (createAlarm()) {
                        // if(db.getAlarm((int) id) != null){
                        //    db.updateAlarm((int) id, data);
                        //} else {
                        db.insertAlarm(Integer.toString((int) id), taskResult, month, day, year, hour, minute, am_pm);
                        Intent resultIntent = new Intent(Notification.this, MainActivity.class);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                    else{
                        check.toggle();
                        Toast.makeText(getApplicationContext(), "Cannot set past time or date", Toast.LENGTH_SHORT).show();
                    }
                }
                if (year == null && hour == null){
                    System.out.println("Enter Time and Date");
                    Toast.makeText(getApplicationContext(), "Enter Time and Date", Toast.LENGTH_SHORT).show();
                    check.toggle();
                }
                else if (hour == null){
                    System.out.println("Enter Time");
                    Toast.makeText(getApplicationContext(), "Enter Time", Toast.LENGTH_SHORT).show();
                    check.toggle();
                }
                else if (year == null){
                    System.out.println("Enter Date");
                    Toast.makeText(getApplicationContext(), "Enter Date", Toast.LENGTH_SHORT).show();
                    check.toggle();
                }

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 123456 && resultCode == RESULT_OK && data != null) {
            year = data.getStringExtra("Year");
            month = data.getStringExtra("Month");
            day = data.getStringExtra("Day");

            dateResult = data.getStringExtra("date");
            dateHeader.setText(dateResult);

        }

        if ((requestCode == 7890) && (resultCode == RESULT_OK) && (data != null)) {
            hour = data.getStringExtra("Hour");
            minute = data.getStringExtra("Minute");
            am_pm = data.getStringExtra("AM_PM");
            System.out.println("ampm is " + am_pm);
            timeResult = data.getStringExtra("time");
            timeHeader.setText(timeResult);
        }
    }

    private Boolean createAlarm() {
        //int interval = 1000 * 60 * 20 * 60;

        System.out.println(month + year + day + " " + hour + ":" + minute);
        pendingIntent = PendingIntent.getBroadcast(Notification.this, (int) id, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (am_pm.equals(" AM") && Integer.valueOf(hour) == 12) {
            calendar.set(Calendar.HOUR, Integer.valueOf(hour) - 12);
        }
        else if (am_pm.equals(" PM") && Integer.valueOf(hour) != 12) {
            calendar.set(Calendar.HOUR, Integer.valueOf(hour) + 12);
        }
        else{
            calendar.set(Calendar.HOUR, Integer.valueOf(hour));
        }
        calendar.set(Calendar.MINUTE, Integer.valueOf(minute));
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(day));
        calendar.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        calendar.set(Calendar.YEAR, Integer.valueOf(year));
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            return false;
        }


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(getApplicationContext(), "Notification Set", Toast.LENGTH_SHORT).show();

        return true;

    }

    //getters
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

    public String getName() {return this.taskResult;}


    //setters
    public void setYear(String year){
        this.year = year;
    }

    public void setMonth(String month){
        this.month = month;
    }

    public void setDay(String day){this.day = day;}

    public void setHour(String hour) {this.hour = hour;}

    public void setMinute(String minute){
        this.minute = minute;
    }

    public void setName(String name) { this.taskResult = name;}
}
