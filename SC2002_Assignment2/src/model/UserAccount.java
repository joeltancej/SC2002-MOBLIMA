package model;

import java.io.Serializable;

public class UserAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int userID;
	private String username;
    private String password;
    private String mobileNumber;
    private String email;
    private int age;
    private boolean isMember;
    
    public UserAccount(int userID,String username,String password,String mobileNumber,String email,int age, boolean member) {
    		
    	this.userID = userID;
    	this.username=username;
    	this.password=password;
    	this.mobileNumber=mobileNumber;
    	this.email=email;
    	this.age=age;
    	this.isMember = member;
    }
    
    public static UserAccount copy(UserAccount another) {
    	UserAccount user = new UserAccount(
    			another.getUserID(),
    			another.getUsername() ,
    			another.getPassword(),
    			another.getMobileNumber(),
    			another.getEmail(),
    			another.getAge(),
    			another.getIsMember()
    	);
    	return user;
    }
    
    public void setMobileNumber(String number){
        this.mobileNumber =  number;
    }
    public String getMobileNumber(){
        return this.mobileNumber;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String name ){
        this.username = name;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password ){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email ){
        this.email = email;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age ){
        this.age = age;
    }

    public int getUserID(){
        return this.userID;
    }

    public void setUserID(int id ){
        this.userID = id;
    }

	public boolean getIsMember(){
		return isMember;
	}

	public void setIsMember(boolean member) {
		this.isMember = member;
	}
}