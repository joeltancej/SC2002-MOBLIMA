package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int movieID;
    private String title;
    private String director;
    private ArrayList<String> casts;
    private String movieContent;
    private double duration;
    private MovieStatus state;
    
    public Movie(int movieID, String title, String director, ArrayList<String> casts, 
    		String movieContent, double duration, MovieStatus state) {
    	this.movieID = movieID;
    	this.title = title;
    	this.director = director;
    	this.casts = casts;
    	this.movieContent = movieContent;
    	this.duration = duration;
    	this.state = state;

    }
    public static Movie copy(Movie another) {
    	Movie movie = new Movie(
    			another.getMovieID(),
    			another.getTitle(),
    			another.getDirector(),
    			another.getCasts(),
    			another.getMovieContent(),
    			another.getDuration(),
    			another.getMovieState()
    	);
    	return movie;
    }
    
	
	public MovieStatus getMovieState() {
		return this.state;
	}
	
	public void setMovieState(MovieStatus movieState) {
		this.state = movieState;
	}

    public int getMovieID(){
        return this.movieID;
    }

    public void setMovieID(int id){
        this.movieID = id;
    }
    
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDirector(){
        return this.director;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public ArrayList<String> getCasts(){
        return this.casts;
    }

    public void setCasts(ArrayList<String> casts){
        this.casts = casts;
    }

    public String getMovieContent(){
        return this.movieContent;
    }

    public void setMovieContent(String content){
        this.movieContent = content;
    }

    public double getDuration(){
        return this.duration;
    }

    public void setDuration(double duration){
        this.duration = duration;
    }


}