package remindersapp.perrypersonalsecretary.dataModels;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import remindersapp.perrypersonalsecretary.Reminder;

// include an import for the Utilities class


/**
 * Created by bkool on 10/17/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String databaseName = "reminderDatabase";
    private static final Integer databaseVersion = 1;

    public DbHelper(Context context) {
        super(context,databaseName,null,databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
