package utils;

import java.util.ArrayList;
import java.util.HashMap;

import database.Data;
import model.*;

public class SearchUtils {
	public static ArrayList<Integer> CinemaIDListByCineplexID(int cineplexID){
		HashMap<Integer, Cinema> cinemaList = Data.cinemaList;
		ArrayList<Integer> arr = new ArrayList<>();
		for (Cinema cinema : cinemaList.values()) {
			  if(cinema.getCineplexID() == cineplexID) {
				  arr.add(cinema.getCinemaID());
			  }
		}
		return arr;
	}
	
	public static Cineplex searchCineplexByName(String name) {
		HashMap<Integer, Cineplex> list = Data.cineplexList;
		for(Cineplex cineplex : list.values()) {
			if(cineplex.getName().equals(name)) {
				return cineplex;
			}
		}
		return null;
	}
	
	//0 -- > by userID, 1 --> by movieID
	public static ArrayList<Integer> BookingIDListByID(int id,int key){
		HashMap<Integer, Booking> bookingList = Data.bookingList;
		ArrayList<Integer> arr = new ArrayList<>();
		for (Booking booking : bookingList.values()) {
			  switch(key) {
				case 0:
					if(booking.getUserID() == id) {
						arr.add(booking.getBookingID());
					}
					break;
				case 1:
					ShowStatus show = SearchUtils.searchShowStatus(booking.getShowStatusID());
					if(show == null) {
						continue;
					}
					if(show.getMovieID() == id) {
						arr.add(booking.getBookingID());
					}
					break;
			}
		}
		return arr;
	}
	
	//0 --> userID, 1-->movieID
	public static ArrayList<Integer> ReviewIDListByID(int id, int key){
		HashMap<Integer, MovieReview> reviewList = Data.movieReviewList;
		ArrayList<Integer> arr = new ArrayList<>();
		for (MovieReview review : reviewList.values()) {
			switch(key) {
				case 0:
					if(review.getUserID() == id) {
						arr.add(review.getReviewID());
					}
					break;
				case 1:
					if(review.getMovieID() == id) {
						arr.add(review.getReviewID());
					}
					break;
					
			}
		}
		return arr;
	}
	
	

	//0 show by cinemaID, 1 --> movieID
	public static ArrayList<Integer> ShowStatusListByID(int id, int key){
		HashMap<Integer, ShowStatus> showStatusList = Data.showStatusList;
		ArrayList<Integer> arr = new ArrayList<>();
		for (ShowStatus buffer : showStatusList.values()) {
			switch(key) {
				case 0:
					if(buffer.getCinemaID() == id) {
						arr.add(buffer.getShowStatusID());
					}
					break;
				case 1:
					if(buffer.getMovieID() == id) {
						arr.add(buffer.getShowStatusID());
					}
					break;
					
			}
		}
		return arr;
	}
	
	
	public static MovieRank searchMovieRank(int id) {
	 if (Data.movieRankList.containsKey(id)) {
		 MovieRank result = Data.movieRankList.get(id);
            return result;
        }
        return null;
	}
	
	public static MovieRank searchMovieRankByMovieID(int movieid) {
		HashMap <Integer,MovieRank> list = Data.movieRankList;
		for(MovieRank buffer : list.values()) {
			if(buffer.getMovieID() == movieid) {
				return buffer;
			}
		}
		return null;
	}
	
	public static Booking searchBooking(int id) {
        if (Data.bookingList.containsKey(id)) {
        	Booking result = Data.bookingList.get(id);
            return result;
        }
        return null;
    }
	
	public static Cinema searchCinema(int id) {
        if (Data.cinemaList.containsKey(id)) {
        	Cinema result = Data.cinemaList.get(id);
            return result;
        }
        return null;
    }
	
	public static Cineplex searchCineplex(int cineplexID) {
        if (Data.cineplexList.containsKey(cineplexID)) {
        	Cineplex result = Data.cineplexList.get(cineplexID);
            return result;
        }
        return null;
    }
	 
	public static Cineplex searchCineplex(String name) {
		 for(Cineplex cineplex: Data.cineplexList.values()) {
				if(name.equals(cineplex.getName())) {
					return cineplex;
				}
			}
			return null;
	 }
	 
	public static Holiday searchHoliday(int holidayId) {
			for(Integer id: Data.holidayList.keySet()) {
				if(id == holidayId) {
					return Data.holidayList.get(id);
				}
			}
			return null;
		}
		
	public static Holiday searchHoliday(String name, DateUtils date) {
		for(Holiday holiday: Data.holidayList.values()) {
			if(name.equals(holiday.getHolidayName()) && DateUtils.equal(date, holiday.getHolidayDate())) {
				return holiday;
			}
		}
		return null;
	}
	
	public static Movie searchMovie(int movieID) {
        if (Data.movieList.containsKey(movieID)) {
        	Movie result = Data.movieList.get(movieID);
            return result;
        }
        return null;
    }
	 
	 
	public static Movie searchMovie(String name) {
		 for(Movie movie: Data.movieList.values()) {
				if(name.equals(movie.getTitle())) {
					return movie;
				}
			}
			return null;
	 }
	 
	public static MovieReview searchMovieReview(int id) {
	        if (Data.movieReviewList.containsKey(id)) {
	        	MovieReview result = Data.movieReviewList.get(id);
	            return result;
	        }
	        return null;
	    }
	 
	public static MovieReview searchMovieReview(int userID, int movieID) {
		HashMap<Integer,MovieReview> list = Data.movieReviewList;
		for(MovieReview review: list.values()) {
			if(review.getUserID() == userID && review.getMovieID() == movieID) {
				return review;
			}
		}
		return null;
    }
	
	public static ShowStatus searchShowStatus(int id) {
			if (Data.showStatusList.containsKey(id)) {
				ShowStatus result = Data.showStatusList.get(id);
	            return result;
	        }
	        return null;
		}
	 
	public static UserAccount searchUserAccount(int userID) {
		 if (Data.userAccountList.containsKey(userID)) {
	        	UserAccount user = Data.userAccountList.get(userID);
	            return user;
	        }
	        return null;
	}
	
	
	public static UserAccount searchUserAccount(String username) {
		 for(UserAccount user: Data.userAccountList.values()) {
				if(username.equals(user.getUsername())) {
					return user;
				}
			}
			return null;
	}
}
