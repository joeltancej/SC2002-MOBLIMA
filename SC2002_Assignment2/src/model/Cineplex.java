package model;

import java.io.Serializable;

public class Cineplex implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cineplexID;
    private String name;
    
    
    public Cineplex(int cineplexID, String name) {
    	this.cineplexID = cineplexID;
    	this.name = name;
    }

    public static Cineplex copy(Cineplex another) {
    	Cineplex cineplex  = new Cineplex(
    			another.getCineplexID(),
    			another.getName()
    	);
    	return cineplex;
    }

	
    public int getCineplexID(){
        return this.cineplexID;
    }

    public void setCineplexID(int id){
        this.cineplexID = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name= name;
    }


}
