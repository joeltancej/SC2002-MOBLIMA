package manager;

import java.util.ArrayList;
import java.util.HashMap;

import database.Data;
import database.FileType;
import model.Holiday;
import utils.DateUtils;
import utils.Helper;
import utils.SearchUtils;
import utils.Validator;

public class HolidayMgr {
	private static HashMap<Integer,Holiday> holidayList = Data.holidayList;
	
	public static ArrayList<Holiday> getAllHolidayList(){
		ArrayList<Holiday> list = new ArrayList<Holiday>();
		for(Holiday holiday : holidayList.values()) {
			list.add(Holiday.copy(holiday));
		}
		return list;
	}
	
	public static int createHoliday(String name, DateUtils date) {
		if(Validator.validateHoliday(name,date)  == true ) {
			return -1;
		}
		int holidayId = Helper.getUniqueId(holidayList);
		Holiday holiday = new Holiday(holidayId,name,date);
		holidayList.put(holidayId, holiday);
		Data.saveFile(FileType.HOLIDAY);
		return holidayId;
	}
	
	public static boolean removeHoliday(int holidayId) {
		if(Validator.validateHoliday(holidayId) == false) {
			return false;
		}
		holidayList.remove(holidayId);
		Data.saveFile(FileType.HOLIDAY);
		return true;
	}

		
	public static boolean removeHoliday(String name, DateUtils date) {
		if(Validator.validateHoliday(name,date) == false) {
			return false;
		}
		Holiday holiday = SearchUtils.searchHoliday(name, date);
		int holidayId = holiday.getHolidayID();
		holidayList.remove(holidayId);
		Data.saveFile(FileType.HOLIDAY);
		return true;
	}
	
	public static boolean updateHolidayName(String name, DateUtils date, String newName) {
		if(Validator.validateHoliday(name,date) == false) {
			return false;
		}
		if(Validator.validateHoliday(newName,date)  == true ) {
			return false;
		}
		Holiday holiday = SearchUtils.searchHoliday(name, date);
		holiday.setHolidayName(newName);
		holidayList.put(holiday.getHolidayID(), holiday);
		Data.saveFile(FileType.HOLIDAY);
		return true;
		
	}
	
	public static boolean updateHolidayName(int holidayID, String newName) {
		if(Validator.validateHoliday(holidayID) == false) {
			return false;
		}
		Holiday holiday = SearchUtils.searchHoliday(holidayID);
		DateUtils date = holiday.getHolidayDate();
		if(Validator.validateHoliday(newName,date)  == true ) {
			return false;
		}
		holiday.setHolidayName(newName);
		holidayList.put(holiday.getHolidayID(), holiday);
		Data.saveFile(FileType.HOLIDAY);
		return true;
	}
	
	public static boolean updateHolidayDate(String name, DateUtils date, DateUtils newDate) {
		if(Validator.validateHoliday(name,date) == false) {
			return false;
		}
		Holiday updateHoliday = SearchUtils.searchHoliday(name, date);
		if(Validator.validateHoliday(updateHoliday.getHolidayName(),newDate)  == true ) {
			return false;
		}
		updateHoliday.setHolidayDate(newDate);
		holidayList.put(updateHoliday.getHolidayID(), updateHoliday);
		Data.saveFile(FileType.HOLIDAY);
		return true;
		
	}
	
	
	public static boolean updateHolidayDate(int holidayID, DateUtils newDate) {
		if(Validator.validateHoliday(holidayID) == false) {
			return false;
		}
		Holiday holiday = SearchUtils.searchHoliday(holidayID);
		if(Validator.validateHoliday(holiday.getHolidayName(),newDate)  == true ) {
			return false;
		}
		holiday.setHolidayDate(newDate);
		holidayList.put(holiday.getHolidayID(), holiday);
		Data.saveFile(FileType.HOLIDAY);
		return true;
		
	}
	
	public static Holiday getHoliday(int holidayID) {
		if(Validator.validateHoliday(holidayID) == false) {
			return null;
		}
		Holiday holiday = SearchUtils.searchHoliday(holidayID);
		return Holiday.copy(holiday);
	}
	
	
	public static Holiday getHoliday(String name, DateUtils date) {
		if(Validator.validateHoliday(name,date)== false) {
			return null;
		}
		Holiday holiday = SearchUtils.searchHoliday(name, date);
		return Holiday.copy(holiday);
	}

	
}
