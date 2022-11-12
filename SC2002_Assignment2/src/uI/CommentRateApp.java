package uI;
import java.util.Scanner;

import manager.MovieMgr;
import manager.ReviewMgr;
import model.Movie;
public class CommentRateApp {
	private static void AddComments(Scanner sc) {
		String str;
		System.out.print("Your review: ");
		sc.nextLine();
		str = sc.nextLine();
		int rating;
		do {
			System.out.print("Your rating from 1-5:  ");
			rating = sc.nextInt();
			if(rating>5 || rating <1) {
				System.out.println("Please rate from 1-5 only.");
			}
			else {
				break;
			}
		}while(true);
		boolean a = ReviewMgr.createMovieReview(AppState.getUserID(), AppState.getMovieID(),rating, str);
		if(!a) {
			System.out.println("It seems that you have rated this movie before. Do you wish to update your rating instead?\n");
			return;
		}
		System.out.println("Review succesfully created.");
	}
	
	
	private static void UpdateComment(Scanner sc) {
		String str;
		System.out.print("Your review: ");
		sc.nextLine();
		str = sc.nextLine();
		ReviewMgr.updateReviewComment(AppState.getUserID(), AppState.getMovieID(), str);
		int rating;
		do {
			System.out.print("Your rating from 1-5:  ");
			rating = sc.nextInt();
			if(rating>5 || rating <1) {
				System.out.println("Please rate from 1-5 only.");
			}
			else {
				break;
			}
		}while(rating>=1 && rating <=5);
		ReviewMgr.updateReviewRating(AppState.getUserID(), AppState.getMovieID(), rating);
		System.out.println("Review succesfully updated.");

	}
	
	public static void AppMain(Scanner sc) {
		
		do {
			int choice =-1;
			System.out.print("\n========================================\n");
			System.out.print("          Reviews And Ratings             \n");
			System.out.print("========================================\n");
			Movie movie = MovieMgr.getMovieByID(AppState.getMovieID());
			System.out.println("Movie title: "+movie.getTitle()+"\n");
			System.out.print("1) Add review and rating\n");
			System.out.print("2) Update review and rating\n");
			System.out.println("0) Back to movie listing\n");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 0:
					return;
				case 1:
					AddComments(sc);
					break;
				case 2:
					UpdateComment(sc);
					break;
				default:
					break;
					
			}
		}while(true);
	}
}
