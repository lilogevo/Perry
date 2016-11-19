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
    public static final String DATABASE_NAME = "alarm.db";
    public static final String TABLE_NAME = "alarmtable";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_MINUTE = "minute";

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
                "create table " + TABLE_NAME + " " +
                        "(id integer primary key, " + COLUMN_MONTH + " integer, " + COLUMN_DAY + " integer, "
                        + COLUMN_YEAR + " integer, " + COLUMN_HOUR + " integer, " + COLUMN_MINUTE + " integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertAlarm(Calendar calendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MONTH, calendar.get(Calendar.MONTH));
        contentValues.put(COLUMN_DAY, calendar.get(Calendar.DATE));
        contentValues.put(COLUMN_YEAR, calendar.get(Calendar.YEAR));
        contentValues.put(COLUMN_HOUR, calendar.get(Calendar.HOUR));
        contentValues.put(COLUMN_MINUTE, calendar.get(Calendar.MINUTE));

        db.insert(TABLE_NAME, null, contentValues);
        return true;
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

    public boolean updateAlarm(Integer id, Calendar calendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_HOUR, calendar.get(Calendar.HOUR));
        contentValues.put(COLUMN_MINUTE, calendar.get(Calendar.MINUTE));
        contentValues.put(COLUMN_MONTH, calendar.get(Calendar.MONTH));
        contentValues.put(COLUMN_DAY, calendar.get(Calendar.DATE));
        contentValues.put(COLUMN_YEAR, calendar.get(Calendar.YEAR));
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteAlarm(Integer id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<Calendar> getAllAlarms() {
        ArrayList<Calendar> arrayList = new ArrayList<Calendar>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(
                    res.getInt(res.getColumnIndex(COLUMN_YEAR)),
                    res.getInt(res.getColumnIndex(COLUMN_MONTH)),
                    res.getInt(res.getColumnIndex(COLUMN_DAY)),
                    res.getInt(res.getColumnIndex(COLUMN_HOUR)),
                    res.getInt(res.getColumnIndex(COLUMN_MINUTE))
            );

            arrayList.add(calendar);
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

    public Calendar getAlarm(int id) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where id=" + id + "", null);
        res.moveToPosition(0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                res.getInt(res.getColumnIndex(COLUMN_YEAR)),
                res.getInt(res.getColumnIndex(COLUMN_MONTH)),
                res.getInt(res.getColumnIndex(COLUMN_DAY)),
                res.getInt(res.getColumnIndex(COLUMN_HOUR)),
                res.getInt(res.getColumnIndex(COLUMN_MINUTE))
        );

        res.close();
        return calendar;
    }
}

