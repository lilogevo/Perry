package a461lapp.perry;

import java.io.Serializable;

/**
 * Created by aftabhadimohd on 11/28/16.
 */

public class Data implements Serializable{

    private String name;
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;

    public void setField (String name, String year, String month, String day, String hour, String minute){
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public String getName(){
        return this.name;
    }

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

    public String getMinute() {
        if ((this.minute).length() == 1){
            return 0 + this.minute;
        } else
            return this.minute;
    }
}
