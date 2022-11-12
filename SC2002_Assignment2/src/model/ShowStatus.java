package model;
import java.io.Serializable;

import utils.*;


public class ShowStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int showStatusID;
	private int cineplexID;
	private int cinemaID;
	private int movieID;
	private DateUtils showDate;
	private TimeUtils showTime;
	private MovieType movieType;
	private SeatType[][] seatStatus;
	
	public ShowStatus(int showStatusID,int cineplexID, int cinemaID, int movieID, DateUtils showDate, 
			TimeUtils showTime, MovieType movieType, SeatType[][] seatStatus) {
		this.showStatusID = showStatusID;
		this.cineplexID=cineplexID;
		this.cinemaID=cinemaID;
		this.movieID=movieID;
		this.showDate=showDate;
		this.showTime=showTime;
		this.movieType=movieType;
		this.seatStatus = seatStatus;
	}
	
	public static ShowStatus copy(ShowStatus another) {
		ShowStatus buffer = new ShowStatus(
				another.getShowStatusID(),
				another.getCineplexID(),
				another.getCinemaID(),
				another.getMovieID(),
				another.getShowDate(),
				another.getShowTime(),
				another.getMovieType(),
				another.getseatStatus()
		);
		return buffer;
	}
	public void setseatStatus(SeatType[][] seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	public SeatType[][] getseatStatus() {
		return this.seatStatus;
	}
	
	public void setMovieType(MovieType id) {
		this.movieType = id;
	}
	
	public MovieType getMovieType() {
		return this.movieType;
	}
	
	public void setMovieID(int id) {
		this.movieID = id;
	}
	
	public int getMovieID() {
		return this.movieID;
	}
	
	public void setCineplexID(int id) {
		this.cineplexID = id;
	}
	
	public int getCineplexID() {
		return this.cineplexID;
	}
	
	public void setCinemaID(int id) {
		this.cinemaID = id;
	}
	
	public int getCinemaID() {
		return this.cinemaID;
	}
	
	public void setShowStatusID(int id) {
		this.showStatusID = id;
	}
	
	public int getShowStatusID() {
		return this.showStatusID;
	}
	
	public DateUtils getShowDate() {
		return this.showDate;
	}
	
	public void setShowDate(DateUtils date) {
		this.showDate = date;
	}
	
	public TimeUtils getShowTime() {
		return this.showTime;
	}
	
	public void setShowTime(TimeUtils time) {
		this.showTime =time;
	} 
}
