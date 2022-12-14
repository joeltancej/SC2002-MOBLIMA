package manager;

import java.util.HashMap;
import java.util.ArrayList;

import database.Data;
import database.FileType;
import model.MovieRank;
import model.MovieReview;
import model.UserAccount;
import utils.Helper;
import utils.SearchUtils;
import utils.Validator;

public class MovieRankMgr {
	private static HashMap<Integer, MovieRank> movieRankList = Data.movieRankList;
	
	public static ArrayList<MovieRank> getAllMovieRankList(){
		ArrayList<MovieRank> list = new ArrayList<>();
		for(MovieRank rank : Data.movieRankList.values()) {
			list.add(MovieRank.copy(rank));
		}
		return list;
	}
	
	public static MovieRank getMovieRankByMovieID(int movieID){
		for(MovieRank rank : Data.movieRankList.values()) {
			if(rank.getMovieID() == movieID) {
				return MovieRank.copy(rank);
			}
		}
		return null;
	}
	
	public static boolean createMovieRank(int movieID, int numRaters, double overallRating, double sales) {
		if(Validator.validateMovie(movieID) == false) {
			return false;
		}
		int movieRankID = Helper.getUniqueId(Data.movieRankList);
		MovieRank newMovieRank = new MovieRank(movieRankID, movieID, numRaters, overallRating, sales);
		Data.movieRankList.put(movieRankID, newMovieRank);
		Data.saveFile(FileType.MOVIE_RANK);
		return true;
	}
	
	public static boolean createMovieRank(int movieID, int numRaters, double overallRating) {
		if(Validator.validateMovie(movieID) == false) {
			return false;
		}
		int movieRankID = Helper.getUniqueId(Data.movieRankList);
		MovieRank newMovieRank = new MovieRank(movieRankID, movieID, numRaters, overallRating);
		Data.movieRankList.put(movieRankID, newMovieRank);
		Data.saveFile(FileType.MOVIE_RANK);
		return true;
	}
	

	public static boolean createMovieRank(int movieID) {
		if(Validator.validateMovie(movieID) == false) {
			return false;
		}
		int movieRankID = Helper.getUniqueId(Data.movieRankList);
		MovieRank newMovieRank = new MovieRank(movieRankID, movieID);
		Data.movieRankList.put(movieRankID, newMovieRank);
		Data.saveFile(FileType.MOVIE_RANK);
		return true;
	}
	

	public static boolean addSales(int movieID, double price) {
		if(Validator.validateMovie(movieID)== false) {
			return false;
		}
		MovieRank buffer2 = SearchUtils.searchMovieRankByMovieID(movieID);
		double preSales = buffer2.getSales();
		buffer2.setSales(preSales+price);
		Data.movieRankList.put(buffer2.getMovieRankID(), buffer2);
		Data.saveFile(FileType.MOVIE_RANK);
		return true;
	}
	
	public static boolean addRating(int movieID,int userID,int rating) {
		if(Validator.validateMovie(movieID)== false || Validator.validateUser(userID) == false) {
			return false;
		}
		MovieRank buffer = SearchUtils.searchMovieRankByMovieID(movieID);
		double overall = buffer.getOverallRating();
		overall = (overall*buffer.getNumRaters() + rating) / (buffer.getNumRaters() +1);
		buffer.setOverallRating(overall);
		buffer.setNumRaters(buffer.getNumRaters()+1);
		Data.movieRankList.put(buffer.getMovieRankID(), buffer);
		Data.saveFile(FileType.MOVIE_RANK);
		return true;
	}
	
	public static boolean changeRating(int movieID,int userID,int rating) {
		if(Validator.validateMovie(movieID)== false ||Validator.validateUser(userID) == false ) {
			return false;
		}
		if(Validator.validateReview(userID, movieID) == false) {
			return false;
		}
		MovieRank buffer = SearchUtils.searchMovieRankByMovieID(movieID);
		MovieReview review = SearchUtils.searchMovieReview(userID, movieID);
		double overall = buffer.getOverallRating();
		overall = overall*buffer.getNumRaters() - review.getRating() + rating;
		overall = overall/buffer.getNumRaters();
		buffer.setOverallRating(overall);
		Data.movieRankList.put(buffer.getMovieRankID(), buffer);
		Data.saveFile(FileType.MOVIE_RANK);
		return true;
	}
}