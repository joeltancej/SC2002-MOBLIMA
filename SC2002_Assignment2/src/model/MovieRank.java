package model;

import java.io.Serializable;

public class MovieRank implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int movieRankID;
	private int movieID;
	private int numRaters;
	private double overallRating;
	private double sales;
	
	public MovieRank(int movieRankID, int movieID, int numRaters, double overallRating, double sales) {
		this.movieRankID = movieRankID;
		this.movieID = movieID;
		this.numRaters = numRaters;
		this.overallRating = overallRating;
		this.sales = sales;
	}
	
	public MovieRank(int movieRankID, int movieID, int numRaters, double overallRating) {
		this.movieRankID = movieRankID;
		this.movieID = movieID;
		this.numRaters = numRaters;
		this.overallRating = overallRating;
		this.sales = 0;
	}
	
	public MovieRank(int movieRankID, int movieID, double sales) {
		this.movieRankID = movieRankID;
		this.movieID = movieID;
		this.numRaters = 0;
		this.overallRating = 0;
		this.sales = sales;
	}
	
	public MovieRank(int movieRankID,int movieID) {
		this.movieRankID = movieRankID;
		this.movieID = movieID;
		this.numRaters = 0;
		this.overallRating = 0;
		this.sales = 0;
	}
	
	public static MovieRank copy(MovieRank another) {
		MovieRank rank = new MovieRank(
				another.movieRankID,
				another.getMovieID(),
				another.getNumRaters(),
				another.getOverallRating(),
				another.getSales()
		);
		return rank;
	}
	public int getMovieRankID() {
		return this.movieRankID;
	}
	
	public void setMovieRankID(int id) {
		this.movieRankID =id;
	}
	public int getMovieID() {
		return this.movieID;
	}
	
	public void setMovieID(int id) {
		this.movieID = id;
	}
	
	public int getNumRaters() {
		return this.numRaters;
	}
	
	public void setNumRaters(int num) {
		this.numRaters = num;
	}
	
	public double getOverallRating() {
		return this.overallRating;
	}
	
	public void setOverallRating(double rating) {
		this.overallRating = rating;
	}
	
	public double getSales() {
		return this.sales;
	}
	
	public void setSales(double sales) {
		this.sales = sales;
	}
}
