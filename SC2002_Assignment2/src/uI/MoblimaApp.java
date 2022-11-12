package uI;

import java.util.Scanner;

import database.Data;
import database.DataInitializer;

public class MoblimaApp {

	public static void displayWelcome() {
		System.out.print("===================================================\n");
		System.out.print("              WELCOME TO MOBLIMA              \n");
		System.out.print("===================================================\n\n");
	}
	
	
	public static int PromptUserInput(Scanner sc) {
		int num =-1;
		System.out.print("\n========================================\n");
		System.out.print("                Main Menu               \n");
		System.out.print("========================================\n");
		System.out.print("1) User login/sign up\n");
		System.out.print("2) Staff login\n");
		System.out.print("3) View movies\n");
		System.out.print("0) Exit\n");
		System.out.print("\nEnter your choice: ");
		num = sc.nextInt();
		return num;
	}
	
	public static int PromptLoginUserInput(Scanner sc) {
		int num =-1;
		System.out.print("\n========================================\n");
		System.out.print("                Main Menu               \n");
		System.out.print("========================================\n");
		System.out.print("1) Logout\n");
		System.out.print("2) View movies\n");
		System.out.print("3) View history\n");
		System.out.print("0) Exit\n");
		System.out.print("\nEnter your choice: ");
		num = sc.nextInt();
		return num;
	}
	
	
	public static void AppMain(Scanner sc) {
		displayWelcome();
		int a;
		do {
			if(AppState.getUserID() == -1) {
				a = PromptUserInput(sc);
				switch(a) {
				case 0:
					Data.saveAllFiles();
					return;
				case 1:
					UserAccountApp.AppMain(sc);
					break;
				case 2:
					CinemaStaffLogin.AppMain(sc);
					break;
				case 3:
					MovieApp.AppMain(sc);
					break;
				default:
					break;
				}
			}
			
			else {
				a = PromptLoginUserInput(sc);
				switch(a) {
				case 0:
					Data.saveAllFiles();
					return;
				case 1:
					AppState.setUserID(-1);
					break;
				case 2:
					MovieApp.AppMain(sc);
					break;
				case 3:
					ViewHistory.AppMain(sc);
					break;
				default:
					break;
				}
			}
		}while(true);
	}
	
}
