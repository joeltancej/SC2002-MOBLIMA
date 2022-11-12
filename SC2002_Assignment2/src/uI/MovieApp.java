package uI;
import java.util.ArrayList;
import java.util.Scanner;

import manager.MovieMgr;
import model.Movie;
import model.MovieStatus;
import utils.*;

public class MovieApp {

	public static void AppMain(Scanner sc) {
		do {
			int num =-1;
			System.out.print("\n========================================\n");
			System.out.print("             Movie Listing              \n");
			System.out.print("========================================\n");
			System.out.print("1) View full movie listing\n");
			System.out.print("2) View top 5 movies by sales\n");
			System.out.print("3) View top 5 movies by overall rating\n");
			System.out.print("4) Search movie\n");
			System.out.print("5) Book tickets\n");
			System.out.print("6) Add movie review/rating\n");
			System.out.print("0) Go back\n");
			System.out.print("\nEnter your choice: ");
			num = sc.nextInt();
			switch(num) {
				case 0:
					return;
				case 1:
					Printer.displayAllMovie(0);
					break;
				case 2:
					Printer.displayMovie(1, 5);
					break;
				case 3:
					Printer.displayMovie(2, 5);
					break;
				case 4:
					Printer.SearchMovie(sc, "", 1);
					break;
				case 5:
					BookTicket(sc);
					break;
				case 6:
					CommentRate(sc);
					break;
				default:
					break;
			}
		}while(true);
	}
	
	public static void CommentRate(Scanner sc) {
		if(AppState.getUserID() ==-1) {
			System.out.print("You must login first before you can rate a movie.\n");
			return;
		}
		int movieID = Printer.SearchMovie(sc, "For Review", 1);
		if(movieID == -1) {
			return;
		}
		AppState.setMovieID(movieID);
		CommentRateApp.AppMain(sc);
		AppState.setMovieID(-1);
		return;
	}
	
	public static void BookTicket(Scanner sc) {
		if(AppState.getUserID() ==-1) {
			System.out.print("You must login first before you can book tickets.\n");
			return;
		}
		int movieID = Printer.SearchMovie(sc, "For Booking", 0);
		if(movieID == -1) {
			return;
		}
		AppState.setMovieID(movieID);
		BookingApp.AppMain(sc);
		AppState.setMovieID(-1);
		return;
	}

}
