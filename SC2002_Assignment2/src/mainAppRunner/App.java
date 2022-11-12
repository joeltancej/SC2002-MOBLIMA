package mainAppRunner;
import java.util.Scanner;

import database.Data;
import uI.MoblimaApp;


public class App {
	
	public static void main (String[] args) {
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			int choice;
			System.out.print("\n========================================\n");
			System.out.print("         Starting Configuration            \n");
			System.out.print("========================================\n");
			System.out.print("1) Initialize Dummy Data\n");
			System.out.print("2) Clear All Data\n");
			System.out.print("3) Start App\n");
			System.out.print("0) Exit\n");
			System.out.print("\nEnter your choice: ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					Data.InitializeData();
					System.out.println("Successfully initialized.");
					break;
				case 2:
					Data.clearAllData();
					System.out.println("Successfully cleared.");
					break;
				case 3:
					Data.readAllFiles();
					MoblimaApp.AppMain(sc);
					break;
				case 0:
					Data.saveAllFiles();
					return;
			}
		}
	}
}
