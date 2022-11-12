package uI;

import java.util.Scanner;

public class PayByCreditCard extends PaymentMethod{
	
	public PayByCreditCard(double price) {
		super(price);
	}
	
	public boolean promptInput(Scanner sc) {
		try {
			
			String number;
			String name;
			System.out.println("Total price: "+super.price);
			System.out.print("Please enter card number: ");
			number = sc.next();
			sc.nextLine();
			System.out.print("Please enter name on card:");
			name = sc.nextLine();
			System.out.print("Please enter CVV: ");
			number = sc.next();
			sc.nextLine();
		} catch (Exception e) {
			System.out.print("Payment failed.");
			return false;
		}
		OnSuccessPayment();
		return true;
	}
	
	public void OnSuccessPayment() {
		System.out.println("Payment successful!");
		System.out.println("Receipt will be sent to you by email. Thank you!");
	}
}