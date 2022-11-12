package uI;
import java.util.Scanner;
import model.*;
import utils.SearchUtils;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import database.Data;
import manager.MovieMgr;

public class MovieConfig {
	
	public static void AppMain(Scanner sc) {
		
		while(true) {
			int a;
			System.out.print("\n========================================\n");
			System.out.print("           Staff Movie Listing            \n");
			System.out.print("========================================\n");
			System.out.print("1) View Full Movie Listing\n");
			System.out.print("2) View Top 5 Movies By Sales\n");
			System.out.print("3) View Top 5 Movies By Overall Rating\n");
			System.out.print("4) Create Movie Listing\n");
			System.out.print("5) Remove Movie Listing\n");
			System.out.print("6) Update Movie Listing\n");
			System.out.println("0) Back to Main Menu\n");
			System.out.print("Please Choose Your Action: ");
			a = sc.nextInt();
			switch(a) {
				case 0:
					return;
				case 1:
					Printer.displayAllMovie(1);
					break;
				case 2:
					Printer.displayMovie(1, 5);
					break;
				case 3:
					Printer.displayMovie(2, 5);
					break;
				case 4:
					AddNewMovie(sc);
					break;
				case 5:
					RemoveMovie(sc);
					break;
				case 6:
					updateMovie(sc);
					break;
				default:
					break;
					
			}
		}
	}
	
	private static void RemoveMovie(Scanner sc) {
		ArrayList<Movie> list = Printer.displayAllMovieTitle();
		System.out.println();
		System.out.print("Enter a movie ID to remove (or enter 0 to return): ");
		int index = sc.nextInt() -1;
		if(index>=list.size() || index<0) {
			return;
		}
		int movieID = list.get(index).getMovieID();
		boolean a = MovieMgr.removeMovie(movieID);
		if(!a) {
			System.out.print("Failed to remove movie.\n");
			return;
		}
		System.out.print("Successfully removed.\n");
	}
	
	public static void AddNewMovie(Scanner sc) {
		
		String title = "", director = "", movieContent = "";
		ArrayList<String> casts =new ArrayList<String>();
		int duration, stateID;
		MovieStatus state = MovieStatus.NOW_SHOWING;
		
		sc.nextLine();
		System.out.print("\nEnter movie title: ");
		title = sc.nextLine();
		
		System.out.print("\nEnter movie director: ");
		director = sc.nextLine();
		
		System.out.print("\nEnter number of movie casts: ");
		int num = sc.nextInt();
		sc.nextLine();
		String castName ="";
		for(int i=0;i<num;i++) {
			System.out.printf("Cast %d: ",i+1);
			castName = sc.nextLine();
			casts.add(castName);
		}
		
		System.out.print("\nEnter synopsis: ");
		movieContent= sc.nextLine();
		
		System.out.print("\nEnter duration: ");
		duration= sc.nextInt();
		sc.nextLine();
		
		System.out.print("\nShowing Status:\n");
		System.out.print("1) COMING SOON  \n");
		System.out.print("2) NOW SHOWING  \n");
		System.out.println("3) NO LONGER SHOWING  \n");
		System.out.print("Enter status ID: ");
		stateID= sc.nextInt();
		switch(stateID) {
			case 1:
				state = MovieStatus.COMING_SOON;
				break;
			case 2:
				state = MovieStatus.NOW_SHOWING;
				break;
			case 3:
				state = MovieStatus.NO_LONGER_SHOWING;
				break;
			default:
				state = MovieStatus.NOW_SHOWING;
				break;
		}
		
		int index = MovieMgr.createMovie(title, director, casts, movieContent, duration, state);
		if(index == -1) {
			System.out.println("Unable to create this movie. Please try again.");
		}
		else {
			System.out.println("Successfully created.");
		}
	}

	private static void updateMovie(Scanner sc) {
		ArrayList<Movie> list = Printer.displayAllMovieTitle();
		System.out.println();
		System.out.print("Enter a movie ID to edit (or enter 0 to return): ");
		int index = sc.nextInt() -1;
		if(index>=list.size() || index<0) {
			return;
		}
		int movieID = list.get(index).getMovieID();
		if(movieID ==-1) {
			return;
		}
		configMovie(sc,movieID);
		return;
	}
	
	public static void configMovie(Scanner sc ,int movieID) {
		do {
			int choice =-1;
			System.out.print("\n========================================\n");
			System.out.print("          Update Movie Listing            \n");
			System.out.print("========================================\n");
			Movie movie = MovieMgr.getMovieByID(movieID);
			System.out.println("Movie title: "+movie.getTitle()+"\n");
			System.out.print("1) Update title \n");
			System.out.print("2) Update director\n");
			System.out.print("3) Update synopsis\n");
			System.out.print("4) Update casts\n");
			System.out.print("5) Update duration\n");
			System.out.print("6) Update movie status\n");
			System.out.println("0) Go back\n");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					return;
				case 1:
					updateMovieString(sc,movieID,1);
					break;
				case 2:
					updateMovieString(sc,movieID,2);
					break;
				case 3:
					updateMovieString(sc,movieID,3);
					break;
				case 4:
					updateCast(sc, movieID);
					break;
				case 5:
					updateDuration(sc, movieID);
					break;
				case 6:
					updateMovieState(sc,movieID);
					break;
				default:
					break;
			}
		}while(true);
	}
	
	public static void updateCast(Scanner sc, int movieID) {
		int choice;
		do {
			System.out.print("\n========================================\n");
			System.out.print("            Update Movie Casts            \n");
			System.out.print("========================================\n");
			Movie movie = MovieMgr.getMovieByID(movieID);
			System.out.println("Movie title: "+movie.getTitle()+"\n");
			System.out.print("1) View cast\n");
			System.out.print("2) Add cast\n");
			System.out.print("3) Remove cast\n");
			System.out.println("0) Go back\n");
			System.out.print("Enter your choice: ");
			
			choice = sc.nextInt();
			String castName;
			switch(choice) {
				case 0:
					return;
				case 1:
					Printer.displayCasts(movieID);
					System.out.println();
					break;
				case 2:
					System.out.print("\nEnter cast name to be added: ");
					sc.nextLine();
					castName = sc.nextLine();
					MovieMgr.addCasts(movieID, castName);
					System.out.print("Successfully updated.\n");
					break;
				case 3:
					System.out.print("\nEnter cast name to be removed: ");
					sc.nextLine();
					castName = sc.nextLine();
					MovieMgr.removeCasts(movieID, castName);
					System.out.print("Successfully updated.\n");
					break;
				default:
					break;
			}
		}while(true);
	}
	
	public static void updateMovieString(Scanner sc, int movieID, int action) {
		sc.nextLine();
		if(action ==1) {
			System.out.print("\nEnter new movie title: ");
			String str;
			str = sc.nextLine();
			MovieMgr.updateMovieTitle(movieID, str);
			System.out.print("Successfully updated.\n");
			return;
		}
		if(action == 2) {
			System.out.print("\nEnter new movie dirctor: ");
			String str;
			str = sc.nextLine();
			MovieMgr.updateMovieDirector(movieID, str);
			System.out.print("Successfully updated.\n");
			return;
		}
		if(action ==  3) {
			System.out.print("\nEnter new movie synopsis: ");
			String str;
			str = sc.nextLine();
			MovieMgr.updateMovieContent(movieID, str);
			System.out.print("Successfully updated.\n");
			return;
		}
	}
	
	public static void updateDuration(Scanner sc, int movieID) {
		System.out.print("\nEnter new duration: ");
		int duration = sc.nextInt();
		MovieMgr.updateMovieDuration(movieID, duration);
		System.out.print("Successfully updated\n");
		return;
	}
	
	public static void updateMovieState(Scanner sc, int movieID) {
		System.out.print("\nShowing Status:\n");
		System.out.print("1) COMING SOON  \n");
		System.out.print("2) NOW SHOWING  \n");
		System.out.println("3) NO LONGER SHOWING  \n");
		System.out.print("Enter new status ID: ");
		int id = sc.nextInt();
		sc.nextLine();
		switch(id){
			case 1: 
				MovieMgr.updateMovieState(movieID,MovieStatus.COMING_SOON);
				break;
			case 2: 
				MovieMgr.updateMovieState(movieID,MovieStatus.NOW_SHOWING);
				break;
			case 3: 
				MovieMgr.updateMovieState(movieID,MovieStatus.NO_LONGER_SHOWING );
				break;
			default:
				System.out.println("Invalid input.\n");
				return;
		}
		System.out.print("Successfully updated.\n");
		
	}
	
}
