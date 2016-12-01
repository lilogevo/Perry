package a461lapp.perry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper mInstance;
    public static final String DATABASE_NAME = "Alarms.db";
    public static final String TABLE_NAME = "alarm_list";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_MINUTE = "minute";
    public static final String COLUMN_AM_PM = "am_pm";

    private Context mCtx;


    public static DBHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DBHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mCtx = context;
    }

    public SQLiteDatabase returnDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + TABLE_NAME + " ( " +
                        COLUMN_ID + " integer primary key, " +
                        COLUMN_NAME + " text, " +
                        COLUMN_DAY + " integer, " +
                        COLUMN_MONTH + " integer, " +
                        COLUMN_YEAR + " integer, " +
                        COLUMN_HOUR + " integer, " +
                        COLUMN_MINUTE + " integer, " +
                        COLUMN_AM_PM + " text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertAlarm(String _id, String _name, String _month, String _day, String _year, String _hour, String _minute, String _ampm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_NAME, notification.getName());
//        contentValues.put(COLUMN_MONTH, notification.getMonth());
//        contentValues.put(COLUMN_DAY, notification.getDay());
//        contentValues.put(COLUMN_YEAR, notification.getYear());
//        contentValues.put(COLUMN_HOUR, notification.getHour());
//        contentValues.put(COLUMN_MINUTE, notification.getMinute());
        contentValues.put(COLUMN_ID, _id);
        contentValues.put(COLUMN_NAME, _name);
        contentValues.put(COLUMN_MONTH, _month);
        contentValues.put(COLUMN_DAY, _day);
        contentValues.put(COLUMN_YEAR, _year);
        contentValues.put(COLUMN_HOUR, _hour);
        contentValues.put(COLUMN_MINUTE, _minute);
        contentValues.put(COLUMN_AM_PM, _ampm);

        System.out.println(_id + _name + _month + _day + _year + _hour + _minute + _ampm);


        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            System.out.println("alarm is not created");
            return false;

        }
        else{
            System.out.println("alarm is created");
            return true;
        }



    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateAlarm(Integer id, Data notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, notification.getName());
        contentValues.put(COLUMN_MONTH, notification.getMonth());
        contentValues.put(COLUMN_DAY, notification.getDay());
        contentValues.put(COLUMN_YEAR, notification.getYear());
        contentValues.put(COLUMN_HOUR, notification.getHour());
        contentValues.put(COLUMN_MINUTE, notification.getMinute());
        contentValues.put(COLUMN_AM_PM, notification.getAm_pm());
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteAlarm(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
         int i = db.delete(TABLE_NAME,
                "id = ? ",
                new String[]{id});
        System.out.println("alarm deleted" + i);

        return i;

    }

    public ArrayList<Data> getAllAlarms() {
        ArrayList<Data> arrayList = new ArrayList<Data>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            Data notification = new Data();
            String id = (String.valueOf(res.getInt(res.getColumnIndex(COLUMN_ID))));
            System.out.println("DBHelper: " +  id);
            String name = (res.getString(res.getColumnIndex(COLUMN_NAME)));
            String year = (String.valueOf(res.getInt(res.getColumnIndex(COLUMN_YEAR))));
            String month = (String.valueOf(res.getInt(res.getColumnIndex(COLUMN_MONTH))));
            String day = (String.valueOf(res.getInt(res.getColumnIndex(COLUMN_DAY))));
            String hour = (String.valueOf(res.getInt(res.getColumnIndex(COLUMN_HOUR))));
            String minute = (String.valueOf(res.getInt(res.getColumnIndex(COLUMN_MINUTE))));
            String ampm = (res.getString(res.getColumnIndex(COLUMN_AM_PM)));
            System.out.println("am pm in dbhelper is: " + ampm);
            notification.setField(id, name, year,month,day,hour,minute, ampm);
            arrayList.add(notification);
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<String> getAllIDs() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }

    public Notification getAlarm(int id) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where id=" + id + "", null);
        res.moveToPosition(0);
        Notification notification = new Notification();
        notification.setName(res.getString(res.getColumnIndex(COLUMN_NAME)));
        notification.setYear(String.valueOf(res.getInt(res.getColumnIndex(COLUMN_YEAR))));
        notification.setMonth(String.valueOf(res.getInt(res.getColumnIndex(COLUMN_MONTH))));
        notification.setDay(String.valueOf(res.getInt(res.getColumnIndex(COLUMN_DAY))));
        notification.setHour(String.valueOf(res.getInt(res.getColumnIndex(COLUMN_HOUR))));
        notification.setMinute(String.valueOf(res.getInt(res.getColumnIndex(COLUMN_MINUTE))));
        res.close();
        return notification;
    }

    public void clearDatabase(String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+TABLE_NAME;
        db.execSQL(clearDBQuery);
    }
}

