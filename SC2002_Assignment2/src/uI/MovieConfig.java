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
			System.out.print("1) View All Movie List\n");
			System.out.print("2) View Top 5 Movie By Sales\n");
			System.out.print("3) View Top 5 Movie By Overall Rating\n");
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
		System.out.print("Enter A Movie ID To Remove (or enter 0 to return): ");
		int index = sc.nextInt() -1;
		if(index>=list.size() || index<0) {
			return;
		}
		int movieID = list.get(index).getMovieID();
		boolean a = MovieMgr.removeMovie(movieID);
		if(!a) {
			System.out.print("Failed To Remove Movie\n");
			return;
		}
		System.out.print("Successfully Removed\n");
	}
	
	public static void AddNewMovie(Scanner sc) {
		
		String title = "", director = "", movieContent = "";
		ArrayList<String> casts =new ArrayList<String>();
		int duration, stateID;
		
		sc.nextLine();
		System.out.print("\nEnter Movie Title: ");
		title = sc.nextLine();
		
		System.out.print("\nEnter Movie Director: ");
		director = sc.nextLine();
		
		System.out.print("\nEnter Number of Movie Casts: ");
		int num = sc.nextInt();
		sc.nextLine();
		String castName ="";
		for(int i=0;i<num;i++) {
			System.out.printf("Cast %d: ",i+1);
			castName = sc.nextLine();
			casts.add(castName);
		}
		
		System.out.print("\nEnter Synopsis: ");
		movieContent= sc.nextLine();
		
		System.out.print("\nEnter Duration: ");
		duration= sc.nextInt();
		sc.nextLine();
		
		MovieStatus state = promptMovieStatus(sc);
		
		MovieAgeR R = promptMovieR(sc);
		
		int index = MovieMgr.createMovie(title, director, casts, movieContent, duration, state,R);
		if(index == -1) {
			System.out.println("Unable to create this movie, Please try again");
		}
		else {
			System.out.println("Successfully created");
		}
	}

	private static void updateMovie(Scanner sc) {
		ArrayList<Movie> list = Printer.displayAllMovieTitle();
		System.out.println();
		System.out.print("Enter A Movie ID To Edit (or enter 0 to return): ");
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
			System.out.println("Movie Title: "+movie.getTitle()+"\n");
			System.out.print("1) Update Title \n");
			System.out.print("2) Update Director\n");
			System.out.print("3) Update Sypnopsis\n");
			System.out.print("4) Update casts\n");
			System.out.print("5) Update duration\n");
			System.out.print("6) Update Movie Status\n");
			System.out.print("7) Update Movie Age Restriction\n");
			System.out.println("0) Go back\n");
			System.out.print("Enter Your Choice: ");
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
					MovieStatus state = promptMovieStatus(sc);
					MovieMgr.updateMovieState(movieID, state);
					System.out.print("Sucessfully Updated\n");
					break;
				case 7:
					MovieAgeR Re = promptMovieR(sc);
					MovieMgr.updateMovieAgeR(movieID, Re);
					System.out.print("Sucessfully Updated\n");
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
			System.out.println("Movie Title: "+movie.getTitle()+"\n");
			System.out.print("1) View casts\n");
			System.out.print("2) Add casts\n");
			System.out.print("3) Remove Casts\n");
			System.out.println("0) Go Back\n");
			System.out.print("Enter Your Choice: ");
			
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
					System.out.print("\nEnter Cast Name to be added: ");
					sc.nextLine();
					castName = sc.nextLine();
					MovieMgr.addCasts(movieID, castName);
					System.out.print("Sucessfully Updated\n");
					break;
				case 3:
					System.out.print("\nEnter Cast Name to be removed: ");
					sc.nextLine();
					castName = sc.nextLine();
					MovieMgr.removeCasts(movieID, castName);
					System.out.print("Sucessfully Updated\n");
					break;
				default:
					break;
			}
		}while(true);
	}
	
	public static void updateMovieString(Scanner sc, int movieID, int action) {
		sc.nextLine();
		if(action ==1) {
			System.out.print("\nEnter New Movie Title: ");
			String str;
			str = sc.nextLine();
			MovieMgr.updateMovieTitle(movieID, str);
			System.out.print("Sucessfully Updated\n");
			return;
		}
		if(action == 2) {
			System.out.print("\nEnter New Movie Dirctor: ");
			String str;
			str = sc.nextLine();
			MovieMgr.updateMovieDirector(movieID, str);
			System.out.print("Sucessfully Updated\n");
			return;
		}
		if(action ==  3) {
			System.out.print("\nEnter New Movie Synopsis: ");
			String str;
			str = sc.nextLine();
			MovieMgr.updateMovieContent(movieID, str);
			System.out.print("Sucessfully Updated\n");
			return;
		}
	}
	
	public static void updateDuration(Scanner sc, int movieID) {
		System.out.print("\nEnter New Duaration: ");
		int duration = sc.nextInt();
		MovieMgr.updateMovieDuration(movieID, duration);
		System.out.print("Sucessfully Updated\n");
		return;
	}
	
	
	public static MovieStatus promptMovieStatus(Scanner sc) {
		int stateID;
		MovieStatus state;
		System.out.print("\nShowing Status:\n");
		System.out.print("1) COMING SOON  \n");
		System.out.print("2) PREVIEW \n");
		System.out.print("3) NOW SHOWING  \n");
		System.out.println("4) NO_LONGER_SHOWING  \n");
		System.out.print("Enter Status ID: ");
		stateID= sc.nextInt();
		switch(stateID) {
			case 1:
				state = MovieStatus.COMING_SOON;
				break;
			case 2:
				state = MovieStatus.PREVIEW;
				break;
			case 3:
				state = MovieStatus.NOW_SHOWING;
				break;
			case 4:
				state = MovieStatus.NO_LONGER_SHOWING;
				break;
			default:
				state = MovieStatus.NOW_SHOWING;
				break;
		}
		return state;
	}
	
	public static MovieAgeR promptMovieR(Scanner sc) {
		MovieAgeR R;
		int stateID;
		System.out.print("\nMovie Age Restriction:\n");
		System.out.print("1) G \n");
		System.out.print("2) PG \n");
		System.out.print("3) PG13  \n");
		System.out.print("4) NC16  \n");
		System.out.print("5) M18  \n");
		System.out.println("6) R21  \n");
		System.out.print("Enter ID: ");
		stateID= sc.nextInt();
		switch(stateID) {
			case 1:
				R = MovieAgeR.G;
				break;
			case 2:
				R = MovieAgeR.PG;
				break;
			case 3:
				R = MovieAgeR.PG13;
				break;
			case 4:
				R = MovieAgeR.NC16;
				break;
			case 5:
				R = MovieAgeR.M18;
				break;
			case 6:
				R = MovieAgeR.R21;
				break;
			default:
				R = MovieAgeR.G;
				break;
		}
		return R;
		
	}
	
}