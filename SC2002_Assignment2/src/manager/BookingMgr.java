package manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.HashMap;
import model.*;

import database.Data;
import database.FileType;
import utils.DateUtils;
import utils.Helper;
import utils.SearchUtils;
import utils.TimeUtils;
import utils.Validator;

public class BookingMgr {
	private static HashMap<Integer, Booking> bookingList = Data.bookingList;
	
	
	public static int createBooking(int userID, int showStatusID, double price,
    		DateUtils bookingDate,TimeUtils bookingTime) {
		if(!(Validator.validateUser(userID) && Validator.validateShowStatus(showStatusID))) {
			return -1;
		}

		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		
		int bookingID = Helper.getUniqueId(bookingList);
		
		Booking newBooking = new Booking(bookingID, userID, showStatusID, price, bookingDate, bookingTime);
		bookingList.put(bookingID, newBooking);
		MovieRankMgr.addSales(buffer.getMovieID(),price);
		Data.saveFile(FileType.BOOKING);
		return bookingID;
		
	}
	
	public static int createBooking(int userID, int showStatusID, double price,
    		LocalDate date,LocalTime time) {
		if(!(Validator.validateUser(userID) && Validator.validateShowStatus(showStatusID))) {
			return -1;
		}

		DateUtils bookingDate = DateUtils.LocalDateToDateUtils(date);
		TimeUtils bookingTime = TimeUtils.LocalTimeToTimeUtils(time);
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		
		int bookingID = Helper.getUniqueId(bookingList);
		
		Booking newBooking = new Booking(bookingID, userID, showStatusID, price, bookingDate, bookingTime);
		
		bookingList.put(bookingID, newBooking);
		MovieRankMgr.addSales(buffer.getMovieID(),price);
		Data.saveFile(FileType.BOOKING);
		return bookingID;
		
	}
	
	public static Booking getBookingCopy(int bookingID) {
		if(!(Validator.validateBooking(bookingID))) {
			return null;
		}
		Booking buffer = SearchUtils.searchBooking(bookingID);
		return Booking.copy(buffer);
	}
	
	public static ArrayList<Booking> getBookingListByUserID(int userID){
		if(!Validator.validateUser(userID) ) {
			return null;
		}
		ArrayList<Booking> list = new ArrayList<Booking>();
		for(Booking buffer: bookingList.values()) {
			if(buffer.getUserID() == userID) {
				list.add(buffer);
			}
		}
		return list;
	}
	
	public static ArrayList<Booking> getAllBookingList(){
		ArrayList<Booking> list = new ArrayList<Booking>();
		for(Booking buffer: bookingList.values()) {
			Booking copy = Booking.copy(buffer);
			list.add(copy);
		}
		return list;
	}
}
