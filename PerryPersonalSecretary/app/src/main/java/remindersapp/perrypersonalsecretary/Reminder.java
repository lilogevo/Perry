package remindersapp.perrypersonalsecretary;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import remindersapp.perrypersonalsecretary.dataModels.DbHelper;

/**
 * Created by bkool on 10/17/2016.
 */
public class Reminder extends Application {
    public static DbHelper dbHelper;
    public static SQLiteDatabase database;
    public static SharedPreferences sharedPref;

    public void onCreate() {
        super.onCreate();

        dbHelper = new DbHelper(this);
        database = dbHelper.getWritableDatabase();

    }
}
