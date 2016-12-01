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
    private String id;
    private String am_pm;

    public void setField (String id, String name, String year, String month, String day, String hour, String minute, String am_pm){
        this.id = id;
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.am_pm = am_pm;
    }

    public String getID() { return this.id;}

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

    public String getAm_pm() {
        System.out.println("am pm in data is: " + this.am_pm);
        return this.am_pm;}

    public String getMinute() {
        if ((this.minute).length() == 1){
            return 0 + this.minute;
        } else
            return this.minute;
    }
}
