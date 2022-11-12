package uI;

import java.util.*;
import manager.*;
import utils.*;
import model.*;

public class SystemConfig {
	
	public static void AppMain(Scanner sc) {
		while(true) {
			int num =-1;
			System.out.print("\n========================================\n");
			System.out.print("              System Config               \n");
			System.out.print("========================================\n");
			System.out.print("1) Edit TicketPrice\n");
			System.out.print("2) Edit Holiday\n");
			System.out.println("0) Go Back\n");
			System.out.print("Enter Your Choice: ");
			num = sc.nextInt();
			
			switch(num) {
				case 0:
					return;
				case 1: 
					EditTicketPrice(sc);
					break;
				case 2:
					EditHoliday(sc);
					break;
				default:
					break;
			}
		}
	}
	
	public static void EditTicketPrice(Scanner sc) {
		while(true) {
			System.out.print("\n========================================\n");
			System.out.print("           Ticket Price Config            \n");
			System.out.print("========================================\n");
			System.out.println("1) Edit Mon To Wed (All sessions) ");
			System.out.println("2) Edit Mon To Wed (All sessions, 3D) ");
			System.out.println("3) Edit Thursday (All sessions) ");
			System.out.println("4) Edit Thursday (All sessions , 3D) ");
			System.out.println("5) Edit Friday (before 6pm) ");
			System.out.println("6) Edit Friday (before 6pm, 3D) ");
			System.out.println("7) Edit Friday (after 6pm) ");
			System.out.println("8) Edit Friday (after 6pm, 3D) ");
			System.out.println("9) Edit WeekEnd (All sessions) ");
			System.out.println("10) Edit WeekEnd (All sessions, 3D)");
			System.out.println("11) Edit WeekDay (Elderly, before 6pm) ");
			System.out.println("12) Edit WeekDay (Student, before 6pm) ");
			System.out.println("13) Edit WeekDay (Student, before 6pm, 3D)  ");
			System.out.println("14) Edit BlockBuster Movie Additional ");
			System.out.println("15) Edit Platinium Suite Additional ");
			System.out.println("16) Edit Gold Suite Additional ");
			System.out.println("17) Edit Holiday Additional ");
			System.out.println("18) Edit Card User Default ");
			System.out.println("19) View All TicketPrice ");
			System.out.println("0) Go Back\n");
			System.out.print("Enter Your Choice: ");
			int choice= sc.nextInt();
			double price;
			
			while(true) {
				if(choice ==0) {
					return;
				}
				if(choice == 19) {
					Printer.displayTicketPrices();
					System.out.println();
					break;
				}
				if(choice>=1 && choice<=18) {
					System.out.print("Enter New Price: ");
					price  = sc.nextDouble();
					TicketPriceMgr.updateTicketPrice(choice-1, price);
					System.out.print("New Price Updated\n");
					break;
				}
			}
		}
	
	}
	
	public static void updateHoliday(Scanner sc, int holidayID) {
		while(true) {
			
			int num =-1;
			System.out.print("1) Update Holiday Name\n");
			System.out.print("2) Update Holiday Date\n");
			System.out.println("0) Go Back\n");
			System.out.print("Enter Your Choice: ");
			num = sc.nextInt();
			sc.nextLine();
			switch(num) {
				case 0:
					return;
				case 1:
					System.out.print("Enter holiday name:");
					String name = sc.nextLine();
					boolean a = HolidayMgr.updateHolidayName(holidayID, name);
					if(a) {
						
						System.out.println("Success\n");
					}
					else {
						System.out.println("Fail\n");
					}
					break;
				case 2:
					System.out.print("Enter holiday date:\n");
					DateUtils date = DateUtils.promptInput(sc);
					boolean b = HolidayMgr.updateHolidayDate(holidayID, date);
					if(b) {
						
						System.out.println("Success\n");
					}
					else {
						System.out.println("Fail\n");
					}
					break;
				default:
					break;
			}
		}
		
		
	}
	
	public static void AddHoliday(Scanner sc) {
		String name;
		sc.nextLine();
		System.out.print("Enter holiday name:");
		name = sc.nextLine();
		
		System.out.print("Enter holiday date:\n");
		DateUtils date = DateUtils.promptInput(sc);
		
		int index = HolidayMgr.createHoliday(name, date);
		if(index == -1) {
			System.out.println("Holiday already existed");
		}
		else {
			System.out.println("Holiday created");
		}
		
	}
	
	
	public static void EditHoliday(Scanner sc) {
		while(true) {
			
			int num =-1;
			System.out.print("\n========================================\n");
			System.out.print("             Holiday Config               \n");
			System.out.print("========================================\n");
			System.out.print("1) View Holiday List\n");
			System.out.print("2) Add Holiday\n");
			System.out.print("3) Edit Holiday\n");
			System.out.print("0) Go Back\n");
			System.out.print("Please Choose Your Action: ");
			num = sc.nextInt();
			
			
			switch(num) {
				case 0:
					return;
				case 1:
					
					Printer.displayHolidayList();
					break;
				case 2:
					AddHoliday(sc);
					break;
				case 3:
					System.out.print("Enter holiday ID: ");
					int id = sc.nextInt();
					Holiday holiday = HolidayMgr.getHoliday(id);
					if(holiday == null) {
						System.out.print("Holiday not exist\n");
						break;
					}
					updateHoliday(sc,id);
					break;
				default:
					break;
				
			}
		}
	}
}