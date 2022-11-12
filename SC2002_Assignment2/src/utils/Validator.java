package utils;

import java.util.ArrayList;
import java.util.HashMap;
import database.Data;
import model.*;

public class Validator {
	// 1 if review exist in the movie
	public static boolean validateReview(int userID, int movieID) {
		ArrayList<Integer> reviewIDs = SearchUtils.ReviewIDListByID(userID, 0);
		HashMap<Integer, MovieReview> reviewList = Data.movieReviewList;
		for(int i=0;i<reviewIDs.size();i++) {
			MovieReview buffer = reviewList.get(reviewIDs.get(i));
			if(buffer.getMovieID() == movieID) {
				return true;
			}
		}
		return false;
	}
	
	// 1 if exist
	public static boolean validateBooking(int id) {
		Booking buffer = SearchUtils.searchBooking(id);
		if(buffer == null) {
			return false;
		}
		return true;
	}
	
	// 1 if exist
	public static boolean validateMovieRank(int Movieid) {
		MovieRank buffer = SearchUtils.searchMovieRankByMovieID(Movieid);
		if(buffer == null) {
			return false;
		}
		return true;
	}

	//1 if exist
	public static boolean validateMovieReview(int reviewID) {
		MovieReview buffer = SearchUtils.searchMovieReview(reviewID);
		if(buffer == null) {
			return false;
		}
		return true;
	}
	
	//1 if exist
	public static boolean validateMovie(int movieID) {
		Movie buffer = SearchUtils.searchMovie(movieID);
		if(buffer == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateMovie(String movieName) {
		Movie buffer = SearchUtils.searchMovie(movieName);
		if(buffer == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateHoliday(int holidayID) {
		Holiday buffer = SearchUtils.searchHoliday(holidayID);
		if(buffer == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateHoliday(String name, DateUtils date) {
		Holiday buffer = SearchUtils.searchHoliday(name,date);
		if(buffer == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateCinema(int cineplexID,int cinemaID) {
		Cinema buffer = SearchUtils.searchCinema(cinemaID);
		if(buffer == null) {
			return false;
		}
		if(buffer.getCineplexID() != cineplexID) {
			return false;
		}
		return true;
	}
	
	public static boolean validateCineplex(int cineplexID) {
		Cineplex buffer = SearchUtils.searchCineplex(cineplexID);
		if(buffer == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateCineplex(String cineplexName) {
		Cineplex buffer = SearchUtils.searchCineplex(cineplexName);
		if(buffer == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateUser(String username) {
		UserAccount buffer = SearchUtils.searchUserAccount(username);
		if(buffer==null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateUser(int userID) {
		UserAccount buffer = SearchUtils.searchUserAccount(userID);
		if(buffer==null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateShowStatus(int showStatusID) {
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		if(buffer == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validateCinema(int cineplexID, String cinemaCode) {
		HashMap<Integer,Cinema> list = Data.cinemaList;
		for(Cinema buffer: list.values()) {
			if(buffer.getCineplexID() == cineplexID) {
				String name = buffer.getCinemaCode();
				if(name.equals(cinemaCode)) {
					return true;
				}
			}
		}
		return false;
	}
}
