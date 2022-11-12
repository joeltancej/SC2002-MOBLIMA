package uI;

import java.util.Scanner;

public class PaymentApp {
	public static boolean AppMain(Scanner sc, double price) {
		int choice;
		System.out.print("Please select payment method:\n");
		System.out.print("1) Credit Card\n");
		System.out.print("2) QR code\n");
		System.out.println("0) Go back\n");
		System.out.print("Enter your choice: ");
		choice = sc.nextInt();
		
		PaymentMethod payment;
		switch(choice) {
		case 1:
			payment = new PayByCreditCard(price);
			return payment.promptInput(sc);
		case 2:
			payment = new PayByQRCode(price);
			return payment.promptInput(sc);
		case 0:
			return false;
		default:
			return false;
		}
	}
	
}