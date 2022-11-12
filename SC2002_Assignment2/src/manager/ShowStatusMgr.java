package manager;

import java.util.ArrayList;
import java.util.HashMap;

import database.Data;
import database.FileType;
import model.*;
import model.SeatType;
import model.ShowStatus;
import utils.DateUtils;
import utils.Helper;
import utils.SearchUtils;
import utils.TimeUtils;
import utils.Validator;

public class ShowStatusMgr {
	private static HashMap<Integer, ShowStatus> showStatusList = Data.showStatusList;
	
	public static ArrayList<ShowStatus> getAllStatusListByMovieID(int movieID){
		ArrayList<ShowStatus> list = new ArrayList<ShowStatus>();
		for(ShowStatus buffer : Data.showStatusList.values()) {
			if(buffer.getMovieID() == movieID) {
				
				list.add(ShowStatus.copy(buffer));
			}
		}
		return list;
	}
	
	public static ArrayList<ShowStatus> getAllStatusList(){
		ArrayList<ShowStatus> list = new ArrayList<ShowStatus>();
		for(ShowStatus buffer : Data.showStatusList.values()) {
			list.add(ShowStatus.copy(buffer));
		}
		return list;
	}
	
	public static ShowStatus getShowStatusByID(int statusID) {
		if(Validator.validateShowStatus(statusID) == false) {
			return null;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(statusID);
		return ShowStatus.copy(buffer);
	}
	
	public static boolean createShowStatus(int cineplexID, int cinemaID, 
			int movieID, DateUtils showDate, TimeUtils showTime, MovieType movieType, SeatType[][] seatStatus) {
		
		if(Validator.validateCineplex(cineplexID) ==false || Validator.validateCinema(cineplexID, cinemaID) ==false) {
			return false;
		}
		if(Validator.validateMovie(movieID) == false) {
			return false;
		}
		int showStatusID = Helper.getUniqueId(showStatusList);
		ShowStatus buffer = new ShowStatus(showStatusID, cineplexID, cinemaID, 
			movieID, showDate, showTime, movieType, seatStatus);
		Data.showStatusList.put(showStatusID, buffer);
		Data.saveFile(FileType.SHOW_STATUS);
		return true;
	}
	
	
	public static boolean createShowStatus(int cineplexID, int cinemaID, 
			int movieID, DateUtils showDate, TimeUtils showTime, MovieType movieType) {
		
		if(Validator.validateCineplex(cineplexID) ==false || Validator.validateCinema(cineplexID, cinemaID) ==false) {
			return false;
		}
		if(Validator.validateMovie(movieID) == false) {
			return false;
		}
		int showStatusID = Helper.getUniqueId(showStatusList);
		Cinema cinema  = SearchUtils.searchCinema(cinemaID);
		SeatType [][] seatStatus = cinema.getSeatPlan();
		ShowStatus buffer = new ShowStatus(showStatusID, cineplexID, cinemaID, 
			movieID, showDate, showTime, movieType, seatStatus);
		Data.showStatusList.put(showStatusID, buffer);
		Data.saveFile(FileType.SHOW_STATUS);
		return true;
	}
	
	public static boolean removeShowStatus(int showStatusID) {
		if(Validator.validateShowStatus(showStatusID) == false) {
			return false;
		}
		Data.showStatusList.remove(showStatusID);
		Data.saveFile(FileType.SHOW_STATUS);
		return true;
	}
	
	public static boolean updateMovie(int showStatusID, int movieID) {
		if(Validator.validateShowStatus(showStatusID) == false || Validator.validateMovie(movieID) == false) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setMovieID(movieID);
		Data.showStatusList.put(buffer.getShowStatusID(), buffer);
		Data.saveFile(FileType.SHOW_STATUS);
		return true;
	}
	
	public static boolean updateMovie(int showStatusID, String movieName) {
		Movie movie = SearchUtils.searchMovie(movieName);
		if(movie == null) {
			return false;
		}
		if(Validator.validateShowStatus(showStatusID) == false) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setMovieID(movie.getMovieID());
		Data.showStatusList.put(buffer.getShowStatusID(), buffer);
		Data.saveFile(FileType.SHOW_STATUS);
		return true;
	}
	
	public static boolean updateShowDate(int showStatusID, DateUtils date) {
		if(Validator.validateShowStatus(showStatusID) ==false) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setShowDate(date);
		Data.showStatusList.put(buffer.getShowStatusID(), buffer);
		Data.saveFile(FileType.SHOW_STATUS);
		return true;
	}
	
	public static boolean updateShowTime(int showStatusID, TimeUtils time) {
		if(Validator.validateShowStatus(showStatusID) ==false) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setShowTime(time);
		Data.showStatusList.put(buffer.getShowStatusID(), buffer);
		Data.saveFile(FileType.SHOW_STATUS);
		return true;
	}
	
	
	public static boolean updateMovieType(int showStatusID, MovieType type) {
		if(Validator.validateShowStatus(showStatusID) ==false) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setMovieType(type);
		Data.showStatusList.put(buffer.getShowStatusID(), buffer);
		Data.saveFile(FileType.SHOW_STATUS);
		return true;
	}
	
	public static SeatType[][] updateCacheSeat(SeatType[][] seats, int row, int col) {
		if(seats == null){
			return null;
		}
		if(row >= seats.length || col>=seats[0].length) {
			return null;
		}
		SeatType type = seats[row][col];
		if(type == null || ValidSeat(type) == false) {
			return null;
		}
		
		int i = row;
		int j = col;
		seats[i][j] = takenSeat(type);
		if(type == SeatType.COUPLE_1) {
			seats[i][j+1] = takenSeat(SeatType.COUPLE_2);
		}
		if(type == SeatType.COUPLE_2) {
			seats[i][j-1] = takenSeat(SeatType.COUPLE_1);
		}
		return seats;
	}
	
	public static SeatType[][] getCopySeatPlan(SeatType[][] type){
		SeatType[][] cpy = new SeatType[type.length][type[0].length];
		for(int i=0;i<type.length;i++) {
			for(int j=0;j<type[0].length;j++) {
				cpy[i][j] = type[i][j];
			}
		}
		return cpy;
	}
	
	public static boolean updateSeat(int showStatusID, SeatType[][] seats) {
		if(Validator.validateShowStatus(showStatusID) ==false) {
			return false;
		}
		ShowStatus buffer = SearchUtils.searchShowStatus(showStatusID);
		buffer.setseatStatus(seats);
		Data.showStatusList.put(buffer.getShowStatusID(), buffer);
		Data.saveFile(FileType.SHOW_STATUS);
		return true;
	}
	
	
	private static boolean ValidSeat(SeatType type) {
		if(type == SeatType.COUPLE_1_T) {
			return false;
		}
		
		if(type == SeatType.COUPLE_2_T) {
			return false;
		}
		
		if(type == SeatType.SINGLE_T) {
			return false;
		}
		
		if(type == SeatType.NOT_EXIST) {
			return false;
		}
		
		return true;
	}
	
	private static SeatType takenSeat(SeatType type) {
		if(type == SeatType.COUPLE_1) {
			return SeatType.COUPLE_1_T;
		}
		
		if(type == SeatType.COUPLE_2) {
			return SeatType.COUPLE_2_T;
		}
		
		if(type == SeatType.SINGLE) {
			return SeatType.SINGLE_T;
		}
		return null;
	}
		
}