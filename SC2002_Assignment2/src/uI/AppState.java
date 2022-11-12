package uI;

public class AppState {
	private static int userID =-1;
	private static int movieID = -1;
	
	public static void setUserID(int id) {
		userID =id;
	}
	public static int getUserID() {
		return userID;
	}
	
	public static void setMovieID(int id) {
		movieID =id;
	}
	public static int getMovieID() {
		return movieID;
	}
}
