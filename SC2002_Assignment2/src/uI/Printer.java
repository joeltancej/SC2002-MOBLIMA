package uI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import database.Data;
import manager.CinemaMgr;
import manager.CineplexMgr;
import manager.HolidayMgr;
import manager.MovieMgr;
import manager.MovieRankMgr;
import manager.ReviewMgr;
import manager.ShowStatusMgr;
import manager.TicketPriceMgr;
import manager.UserAccountMgr;
import model.*;
import utils.DateUtils;
import utils.Helper;
import utils.SearchUtils;
import utils.TimeUtils;
import utils.Validator;

public class Printer {
	
	public static void displayTicketPrices() {
		TicketPrice buffer = TicketPriceMgr.getTicketPrice();
		System.out.println("Mon To Wed (All sessions): "+buffer.getMonWed());
		System.out.println("Mon To Wed (All sessions, 3D): "+buffer.getMonWed3d());
		System.out.println("Thursday (All sessions): "+buffer.getThu());
		System.out.println("Thursday (All sessions , 3D): "+buffer.getThu3d());
		System.out.println("Friday (before 6pm): "+buffer.getFri());
		System.out.println("Friday (before 6pm, 3D): "+buffer.getFri3d());
		System.out.println("Friday (after 6pm): "+buffer.getFriEve());
		System.out.println("Friday (after 6pm, 3D): "+buffer.getFriEve3d());
		System.out.println("WeekDay (Elderly, before 6pm): "+buffer.getElderlyWeekDay());
		System.out.println("WeekDay (Student, before 6pm): "+buffer.getStudentWeekDay());
		System.out.println("WeekDay (Student, before 6pm, 3D) : "+buffer.getStudentWeekDay3d());
		System.out.println("WeekEnd (All sessions): "+buffer.getWeekEnd());
		System.out.println("WeekEnd (All sessions, 3D): "+buffer.getWeekEnd3d());
		System.out.println("BlockBuster Movie Additional: "+buffer.getBlockBusterAdd());
		System.out.println("Platinium Suite Additional: "+buffer.getPlatPriceAdd());
		System.out.println("Gold Suite Additional: "+buffer.getGoldPriceAdd());
		System.out.println("Holiday Additional: "+buffer.getHolidayAdd());
		System.out.println("BlockBuster Additional: "+buffer.getBlockBusterAdd());
		System.out.println("Card User Default: "+buffer.getCard());

	}
	
	public static boolean printCheckAge(MovieAgeR movieAgeR, int age) {
		if(movieAgeR == MovieAgeR.NC16 && age <16) {
			System.out.println("Your must be at least 16 years old to book this movie!");
			return false;
		}
		
		if(movieAgeR == MovieAgeR.M18 && age <18) {
			System.out.println("Your must be at least 18 years old to book this movie!");
			return false;
		}
		
		if(movieAgeR == MovieAgeR.R21 && age <21) {
			System.out.println("Your must be at least 21 years old to book this movie!");
			return false;
		}
		
		return true;
	}
	
	public static void displayHolidayList() {
		ArrayList<Holiday> list = HolidayMgr.getAllHolidayList();
		System.out.println();
		for(int i =0;i<list.size();i++) {
			Holiday holiday = list.get(i);
			System.out.print("Holiday ID: ");
			System.out.println(holiday.getHolidayID());
			System.out.print("Holiday Name: ");
			System.out.println(holiday.getHolidayName());
			System.out.print("Holiday Date: ");
			DateUtils.print(holiday.getHolidayDate());
			System.out.println();
			System.out.println();
		}
	}
	
	public static ArrayList<Movie> displayAllMovieTitle(){
		ArrayList<Movie> movieList = MovieMgr.getAllMovieList();
		if(movieList.size() ==0) {
			System.out.print("\nShowTime Is Empty\n");
			return movieList;
		}
		for(int i=0;i<movieList.size();i++) {
			Movie movie = movieList.get(i);
			System.out.print((i+1)+")  ");
			System.out.println(movie.getTitle());
		}
		return movieList;
	}
	
	public static ArrayList<ShowStatus> displayAllMovieShowTime() {
		ArrayList<ShowStatus> showStatusList = ShowStatusMgr.getAllStatusList();
		for(ShowStatus buffer : Data.showStatusList.values()) {
			System.out.print(buffer.getShowStatusID());
		}
		if(showStatusList.size() ==0) {
			System.out.print("\nShowTime Is Empty\n");
			return showStatusList;
		}
		for(int i=0;i<showStatusList.size();i++) {
			
			Movie movie = MovieMgr.getMovieByID(showStatusList.get(i).getMovieID());
			TimeUtils showtime = showStatusList.get(i).getShowTime();
			DateUtils showDate = showStatusList.get(i).getShowDate();
			int cineplexID = showStatusList.get(i).getCineplexID();
			int cinemaID = showStatusList.get(i).getCinemaID();
			Cinema cinema = CinemaMgr.getCinemaByID(cinemaID);
			ShowStatus status = showStatusList.get(i);
			Cineplex cineplex = CineplexMgr.getCineplexByID(cineplexID);
			
			System.out.println("\n"+(i+1)+")");
			System.out.println("Movie Title: "+movie.getTitle());
			System.out.println("MovieType: "+status.getMovieType());
			System.out.println("Cineplex Name: "+cineplex.getName());
			System.out.println("Cinema Code: "+cinema.getCinemaCode());
			System.out.println("Cinema Class: "+cinema.getCinemaType());
			System.out.print("ShowDate: ");
			DateUtils.print(showDate);
			System.out.println();
			System.out.print("ShowTime: ");
			TimeUtils.print(showtime);
			System.out.println("\n");
		}
		return showStatusList;
		
	}
	
	public static ArrayList<ShowStatus> displaytMovieShowTime(int movieID) {
		System.out.println("Available Show Time:");
		ArrayList<ShowStatus> showStatusList = ShowStatusMgr.getAllStatusListByMovieID(movieID);
		if(showStatusList.size() ==0) {
			System.out.print("\nShowTime Is Empty\n");
			return showStatusList;
		}
		Movie movie = MovieMgr.getMovieByID(movieID);
		for(int i=0;i<showStatusList.size();i++) {
			TimeUtils showtime = showStatusList.get(i).getShowTime();
			DateUtils showDate = showStatusList.get(i).getShowDate();
			int cineplexID = showStatusList.get(i).getCineplexID();
			int cinemaID = showStatusList.get(i).getCinemaID();
			Cinema cinema = CinemaMgr.getCinemaByID(cinemaID);
			ShowStatus status = showStatusList.get(i);
			Cineplex cineplex = CineplexMgr.getCineplexByID(cineplexID);
			
			System.out.println("\n"+(i+1)+")");
			System.out.println("Movie Title: "+movie.getTitle());
			System.out.println("MovieType: "+status.getMovieType());
			System.out.println("Cineplex Name: "+cineplex.getName());
			System.out.println("Cinema Code: "+cinema.getCinemaCode());
			System.out.println("Cinema Class: "+cinema.getCinemaType());
			System.out.print("ShowDate: ");
			DateUtils.print(showDate);
			System.out.println();
			System.out.print("ShowTime: ");
			TimeUtils.print(showtime);
			System.out.println("\n");
		}
		return showStatusList;
	}
	
	public static void displayCasts(int movieID) {
		System.out.println("Casts: ");
		ArrayList<String> casts = MovieMgr.getCastsByMovieID(movieID);
		for(int i=0;i<casts.size();i++) {
			System.out.print(casts.get(i)+", ");
		}
		System.out.println();
	}
	
	
	public static void displayUserCred(int userID) {
		UserAccount user = UserAccountMgr.getUserAccount(userID);
		if(user == null) {
			System.out.print("User Not found\n");
			return;
		}
		System.out.println("UserID: "+user.getUserID());
		System.out.println("Username: "+user.getUsername());
		System.out.println("Password: "+user.getUsername());
		System.out.println("MobileNumber: "+user.getMobileNumber());
		System.out.println("Email: "+user.getEmail());
		System.out.println("Age: "+user.getAge());
	}
	
	//0 no sort, 1--> sales, 2-->rating
	public static void displayMovie(int key, int limit) {
		ArrayList<MovieRank> movieRankList = MovieRankMgr.getAllMovieRankList();
		List<MovieRank> arr = new ArrayList<>();
		for(int i=0;i<movieRankList.size();i++) {
			arr.add(movieRankList.get(i));
		}

		if(key ==1) {
			Collections.sort(arr, new Comparator<MovieRank>() {
				@Override
				public int compare(MovieRank rank2, MovieRank rank1) {
					return ( rank1.getSales()>rank2.getSales() ? 1 : (rank1.getSales() < rank2.getSales() ? -1 : 0));
				}
			});
			
			for(int i=0;i<limit;i++) {
				if(i>=arr.size())return;
				Movie movie = MovieMgr.getMovieByID(arr.get(i).getMovieID());
				System.out.println(i+1+") " + movie.getTitle());
				System.out.println("   Total Sales: "+arr.get(i).getSales()+"\n");
			}
		}
		else if (key==2) {
			Collections.sort(arr, new Comparator<MovieRank>() {
				@Override
				public int compare(MovieRank rank2, MovieRank rank1) {
					return ( rank1.getOverallRating()>rank2.getOverallRating() ? 1 : (rank1.getOverallRating() < rank2.getOverallRating() ? -1 : 0));
				}
			});
			
			for(int i=0;i<limit;i++) {
				if(i>=arr.size())return;
				Movie movie = MovieMgr.getMovieByID(arr.get(i).getMovieID());
				System.out.println(i+1+") " + movie.getTitle());
				String overall =  String.format("%.1f", arr.get(i).getOverallRating());
				System.out.println("   Overall Rating: "+overall+"\n");
			}
		}	
	}
	
	//show ==0, then , dont show No_LONGER_SHOWING
	public static void displayAllMovie(int show) {
		ArrayList<Movie> movieList = MovieMgr.getAllMovieList();
		for(int i=0;i<movieList.size();i++) {
			Movie movie = movieList.get(i);
			if(movie.getMovieState() == MovieStatus.NO_LONGER_SHOWING && show == 0) {
				continue;
			}
			displayMovieDetails(movie.getMovieID());
		}
	}
	
	
	public static void displayMovieDetails(int movieID) {
		Movie movie = MovieMgr.getMovieByID(movieID);
		if(movie == null) {
			System.out.println("Movie Not Found");
			return;
		}
		System.out.println("\n"+(movieID+1)+")");
		System.out.println("Title: "+movie.getTitle());
		System.out.println("Movie Age Restriction: "+movie.getMovieAgeR());
		System.out.println("Showing status: "+ movie.getMovieState());
		System.out.println("Director: "+ movie.getDirector());
		System.out.println("SYNOPSIS: "+ movie.getMovieContent());
		System.out.print("Cast: ");
		for(int i=0;i<movie.getCasts().size();i++) {
			System.out.print(movie.getCasts().get(i)+", ");
		}
		System.out.println();
		displayOverallRating(movieID);
		System.out.print("==============================\n");
		System.out.print("          PAST REVIEW         \n");
		System.out.print("==============================\n");
		displayMovieReviews(movieID);
		System.out.println();
	}
	
	
	public static void displayOverallRating(int movieID) {
		MovieRank buffer = MovieRankMgr.getMovieRankByMovieID(movieID);
		if(buffer == null) {
			System.out.print("Movie Not found\n");
			return;
		}
		double rating = buffer.getOverallRating();
		String overall =  String.format("%.1f", rating);
		int raters = buffer.getNumRaters();
		if(raters>1) {
			System.out.println("Overall Rating: "+overall);
		}
		else {
			System.out.println("Overall Rating: NA");
		}
	}
	
	
	public static void displayMovieReviews(int movieID) {
		ArrayList<MovieReview> reviewList = ReviewMgr.getReveiwListByMovieID(movieID);
		if(reviewList.size() == 0) {
			System.out.print("Currently No Reviews\n");
			return;
		}
		for(int i=0;i<reviewList.size();i++) {
			MovieReview buffer = reviewList.get(i);
			System.out.print((i+1)+")");
			if(buffer.getRating()>0) {	
				System.out.println(" Rating: "+buffer.getRating());
			}else {
				System.out.println(" Rating: NA");
			}
			if(buffer.getComment() != null) {
				System.out.println("   Comment: "+buffer.getComment()+"\n");
			}else {
				System.out.println("   Comment: NA\n");
			}
		}
	}
	
	//book =0, view =1
	public static int SearchMovie(Scanner sc, String str, int bookView) {
		String input;
		sc.nextLine();
		while(true) {
			System.out.print("\nEnter movie title: ");
			input = sc.nextLine();
			ArrayList<Movie> result = new ArrayList<Movie>();
			if(bookView ==0) {
				result = Helper.SearchResultsForBooking(input);
			}else {
				result =Helper.SearchResultsForViewing(input);
			}
			if(result.size() == 0) {
				System.out.println("\nNo Movie Found\n");
				return -1;
			}
			System.out.println("\nSearch Results:\n");
			for(int j=0;j<result.size();j++) {
				System.out.println(j+1+") "+result.get(j).getTitle());
			}
			int resultIndex;
			System.out.println();
			while(true) {	
				System.out.print("Please Enter Movie ID "+str+"(or enter 0 to quit): ");
				resultIndex = sc.nextInt() -1;
				sc.nextLine();
				if(resultIndex <= -1) {
					return -1;
				}
				if(resultIndex>= result.size()) {
					System.out.println("Please choose a valid ID "+ str+ "\n");
				}else {
					break;
				}
			}
			Movie movie = result.get(resultIndex);
			Printer.displayMovieDetails(movie.getMovieID());
			return movie.getMovieID();
		}
	}
}