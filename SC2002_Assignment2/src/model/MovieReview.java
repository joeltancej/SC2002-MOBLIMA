package model;

import java.io.Serializable;

public class MovieReview implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int reviewID;
	private int userID;
	private int movieID;
	private int rating;
	private String comment;
	
	
	public MovieReview(int reviewID, int userID, int movieID, int rating, String comment) {
		this.reviewID = reviewID;
		this.userID = userID;
		this.movieID = movieID;
		this.rating = rating;
		this.comment = comment;
	}
	
	public MovieReview(int reviewID, int userID, int movieID,String comment) {
		this.reviewID = reviewID;
		this.userID = userID;
		this.movieID = movieID;
		this.rating = 0;
		this.comment = comment;
	}
	
	public MovieReview(int reviewID, int userID, int movieID, int rating) {
		this.reviewID = reviewID;
		this.userID = userID;
		this.movieID = movieID;
		this.rating = rating;
		this.comment = null;
	}
	
	public static MovieReview copy(MovieReview another) {
		MovieReview review = new MovieReview(
				another.getReviewID(),
				another.getUserID(),
				another.getMovieID(),
				another.getRating(),
				another.getComment()
		);
		return review;
	}
	
	public void setReviewID(int id) {
		this.reviewID = id;
	}
	public int getReviewID() {
		return this.reviewID;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setUserID(int id) {
		this.userID =id;
	}
	
	public int getMovieID() {
		return this.movieID;
	}
	
	public void setMovieID(int id) {
		this.movieID =id;
	}
}
