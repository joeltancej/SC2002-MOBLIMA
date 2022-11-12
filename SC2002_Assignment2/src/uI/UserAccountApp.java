package uI;
import java.util.*;

import manager.UserAccountMgr;
public class UserAccountApp {
	public static int LoginSignUp(Scanner sc) {
		int num =-1;
		System.out.print("\n========================================\n");
		System.out.print("             Movie Listing              \n");
		System.out.print("========================================\n");
		System.out.print("\n1: Login\n");
		System.out.print("2: SignUp\n");
		System.out.print("0: Back to Main Menu\n");
		System.out.print("Please Choose Your Action: ");
		num = sc.nextInt();
		return num;
	}
	
	public static void AppMain(Scanner sc) {
		int a;
		boolean b =false;
		do {
			int num =-1;
			System.out.print("\n========================================\n");
			System.out.print("              User Account                \n");
			System.out.print("========================================\n");
			System.out.print("1) Login\n");
			System.out.print("2) SignUp\n");
			System.out.println("0) Go Back\n");
			System.out.print("Enter Your Choice: ");
			num = sc.nextInt();
			switch(num) {
				case 0:
					return;
				case 1:
					b = displayLogin(sc);
					if(b) {
						return;
					}
					break;
				case 2:
					displaySignUp(sc);
				default:
					break;
			}
		}while(true);
	}
	
	
	public static boolean displayLogin(Scanner sc) {
		String str, str2;
		System.out.print("\n========================================\n");
		System.out.print("               User Login                 \n");
		System.out.print("========================================\n");
		System.out.print("Enter Username: ");
		sc.nextLine();
		str = sc.nextLine();
		System.out.print("Enter Password: ");
		str2 = sc.nextLine();
		int userID =UserAccountMgr.validateUserLogin(str, str2);
		if(userID <0) {
			System.out.println("Login Failed, Please Try Again\n");
			return false;
		}
		else {
			AppState.setUserID(userID);
			System.out.println("Log in Sucess\n");
			return true;
		}
	}
	
	public static boolean displaySignUp(Scanner sc) {
		//String username,String password,String mobileNumber,String email,int age
		String str, str2,str3,str4;
		int age;
		System.out.print("\n========================================\n");
		System.out.print("              User Sign Up                \n");
		System.out.print("========================================\n");
		System.out.print("Enter Your Username: ");
		sc.nextLine();
		str = sc.nextLine();
		System.out.print("Enter Your Password: ");
		str2 = sc.nextLine();
		System.out.print("Enter Your MobileNumber: ");
		str3 = sc.nextLine();
		System.out.print("Enter Your Email: ");
		str4 = sc.nextLine();
		System.out.print("Enter Your Age: ");
		age = sc.nextInt();
		System.out.println("Do you want to be a member? (Y/N): ");
		boolean member = sc.next().equals("Y") ? true : false;
		boolean a =UserAccountMgr.createUserAccount(str, str2, str3, str4, age, member);
		if(!a) {
			System.out.println("Sign Up Failed, Please Try Again\n");
			return false;
		}
		else {
			System.out.println("Sign Up Sucess, Please try Login\n");
			return true;
		}
	}
}