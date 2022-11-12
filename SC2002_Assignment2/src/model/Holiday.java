package model;

import java.io.Serializable;

import utils.DateUtils;

public class Holiday implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String holidayName;
    private int holidayID;
    private DateUtils holidayDate;

    public Holiday() {};
    
    public Holiday(int id, String name, DateUtils date){
        this.holidayName = name;
        this.holidayID = id;
        this.holidayDate = date;
    }
    
    public static Holiday copy(Holiday another) {
    	Holiday holiday = new Holiday(
    		another.getHolidayID(),
    		another.getHolidayName(),
    		another.getHolidayDate()
    	);
    	return holiday;
    }
    
    public int getHolidayID() {
    	return this.holidayID;
    }
    
    public void setHolidayID(int id) {
    	this.holidayID = id;
    }
    public String getHolidayName(){
        return this.holidayName;
    }

    public void setHolidayName(String name){
        this.holidayName = name;
    }

    public DateUtils getHolidayDate(){
        return this.holidayDate;
    }

    public void setHolidayDate(DateUtils date){
        this.holidayDate = date;
    }
}
