package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import manager.MovieMgr;
import manager.ShowStatusMgr;
import model.Movie;
import model.MovieStatus;
import model.ShowStatus;
import uI.Printer;
import utils.TimeUtils;
public class Helper {
	public static <I,V> int getUniqueId(HashMap<I,V> hashMap) {
		int max =-1;
		for (I id : hashMap.keySet()) {
			  if(id instanceof Integer) {
				  int buffer = (Integer) id;
				  if(buffer>max) {
					  max = buffer;
				  }
			  }
		}
		return max+1;
	}
	
	public static ArrayList<Movie> SearchResultsForBooking(String name){
		ArrayList<Movie> list = MovieMgr.getAllMovieList();
		ArrayList<Movie> result = new ArrayList<Movie>();
		for(int i=0;i<list.size();i++) {
			Movie buffer = list.get(i);
			name = name.toLowerCase();
			String title = buffer.getTitle().toLowerCase();
			if(title.contains(name)) {
				if(buffer.getMovieState() == MovieStatus.NOW_SHOWING || buffer.getMovieState() == MovieStatus.PREVIEW) {
					result.add(buffer);
				}
			}
		}
		return result;
	}
	
	public static ArrayList<Movie> SearchResultsForViewing(String name){
		ArrayList<Movie> list = MovieMgr.getAllMovieList();
		ArrayList<Movie> result = new ArrayList<Movie>();
		for(int i=0;i<list.size();i++) {
			Movie buffer = list.get(i);
			name = name.toLowerCase();
			String title = buffer.getTitle().toLowerCase();
			if(title.contains(name)) {
				result.add(buffer);
			}
		}
		return result;
	}
}
