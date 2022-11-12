package manager;


import java.util.HashMap;
import java.util.ArrayList;
import database.Data;
import database.FileType;
import utils.Helper;
import utils.SearchUtils;
import utils.Validator;
import model.*;


public class CinemaMgr {
	
	public static int createCinema(int cineplexID, CinemaType type,SeatType[][] seatPlan, String cinemaCode) {
		if(Validator.validateCinema(cineplexID, cinemaCode) == true) {
			return -1;
		}
		int cinemaID = Helper.getUniqueId(Data.cinemaList);
		Cinema newCinema = new Cinema(cinemaID,cineplexID,type,seatPlan,cinemaCode);
		Data.cinemaList.put(cinemaID, newCinema);
		Data.saveFile(FileType.CINEMA);
		return cinemaID;
	}
	
	public static Cinema getCinemaByID(int cinemaID) {
		for(Cinema cinema: Data.cinemaList.values()) {
			if(cinema.getCinemaID() == cinemaID) {
				return Cinema.copy(cinema);
			}
		}
		return null;
	}
	public static ArrayList<Cinema> getAllCinemaList(){
		ArrayList<Cinema> list = new ArrayList<Cinema>();
		for(Cinema cinema: Data.cinemaList.values()) {
			Cinema buffer = Cinema.copy(cinema);
			list.add(buffer);
		}
		return list;
	}
	
	public static ArrayList<Cinema> getCinemaListByCineplexID(int cineplexID){
		if(Validator.validateCineplex(cineplexID) == false) {
			return null;
		}
		ArrayList<Cinema> list = new ArrayList<Cinema>();
		for(Cinema cinema: Data.cinemaList.values()) {
			if(cinema.getCineplexID() == cineplexID) {
				Cinema buffer = Cinema.copy(cinema);
				list.add(buffer);
			}
		}
		return list;
	}
}