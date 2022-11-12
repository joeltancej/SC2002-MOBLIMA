package manager;


import java.util.HashMap;

import database.Data;
import database.FileType;
import model.UserAccount;
import utils.Helper;
import utils.SearchUtils;
import utils.Validator;

public class UserAccountMgr {
	private static HashMap<Integer,UserAccount> userAccountList=  Data.userAccountList;
	
	public static UserAccount getUserAccount(int userID) {
		if(Validator.validateUser(userID) ==false) {
			return null;
		}
		UserAccount user = SearchUtils.searchUserAccount(userID);
		return UserAccount.copy(user);
		
	}
	
	//userAccount cant be remove
	public static boolean createUserAccount(String username,String password,String mobileNumber,String email,int age, boolean isMember) {
		if(Validator.validateUser(username) == true) {
			return false;
		}
		int userID = Helper.getUniqueId(userAccountList);
		UserAccount newUser = new UserAccount(userID,username,password,mobileNumber,email,age,isMember);
		userAccountList.put(userID, newUser);
		Data.saveFile(FileType.USER);
		return true;
	}
	
	public static boolean updateMember(int userID, boolean isMember) {
		if(Validator.validateUser(userID) == false) {
			return false;
		}
		UserAccount updateUser = SearchUtils.searchUserAccount(userID);
		updateUser.setIsMember(isMember);
		userAccountList.put(userID, updateUser);
		Data.saveFile(FileType.USER);
		return true;
	}
	
	public static boolean updateEmail(int userID, String text) {
		if(Validator.validateUser(userID) == false) {
			return false;
		}
		UserAccount updateUser = SearchUtils.searchUserAccount(userID);
		updateUser.setEmail(text);
		userAccountList.put(userID, updateUser);
		Data.saveFile(FileType.USER);
		return true;
	}
	
	public static boolean updateMobileNumber(int userID, String text) {
		if(Validator.validateUser(userID) == false) {
			return false;
		}
		UserAccount updateUser = SearchUtils.searchUserAccount(userID);
		updateUser.setMobileNumber(text);
		userAccountList.put(userID, updateUser);
		Data.saveFile(FileType.USER);
		return true;
	}
	
	public static boolean updatePassword(int userID, String text) {
		if(Validator.validateUser(userID) == false) {
			return false;
		}
		UserAccount updateUser = SearchUtils.searchUserAccount(userID);
		updateUser.setPassword(text);
		userAccountList.put(userID, updateUser);
		Data.saveFile(FileType.USER);
		return true;
	}
	
	public static boolean updateUsername(int userID, String text) {
		if(Validator.validateUser(userID) == false) {
			return false;
		}
		UserAccount updateUser = SearchUtils.searchUserAccount(userID);
		if(Validator.validateUser(text) == true) {
			return false;
		}
		updateUser.setUsername(text);
		userAccountList.put(userID, updateUser);
		Data.saveFile(FileType.USER);
		return true;
	}
	
	public static boolean updateUserAge(int userID, int num) {
		if(Validator.validateUser(userID)== false) {
			return false;
		}
		UserAccount updateUser = SearchUtils.searchUserAccount(userID);

		updateUser.setAge(num);
		userAccountList.put(userID, updateUser);
		Data.saveFile(FileType.USER);
		return true;
	}
	
	
	public static int validateUserLogin(String username, String password) {
		if(Validator.validateUser(username) == false) {
			return -2;
		}
		
		UserAccount user = SearchUtils.searchUserAccount(username);
		if(!password.equals(user.getPassword())) {
			return -1;
		}
		return user.getUserID();
	}
	
}