package uI;

import java.util.Scanner;

public class StaffConfiguration {
	
	public static int PromptInput(Scanner sc) {
		int num =-1;
		System.out.print("\n1: Movie listing\n");
		System.out.print("2: Showtime list \n");
		System.out.print("3: System settings\n");
		System.out.print("0: Go back\n");
		System.out.print("Please Choose Your Action: ");
		num = sc.nextInt();
		return num;
	}
	
	public static void AppMain(Scanner sc) {
		while(true) {
			int a = PromptInput(sc);
			switch(a) {
			case 0:
				return;
				case 1: 
					MovieConfig.AppMain(sc);
					break;
				case 2:
					ShowTimeConfig.AppMain(sc);
					break;
				case 3:
					SystemConfig.AppMain(sc);
					break;
				default:
					break;
			}
		}
	}
}
