package uI;

import java.util.Scanner;

public abstract class PaymentMethod {
	public double price;
	
	public PaymentMethod(double price) {
		this.price = price;
	}
	public abstract boolean promptInput(Scanner sc);
	public abstract void OnSuccessPayment();
}