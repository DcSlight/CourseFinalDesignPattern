package Invoice;

import Components.Customer;
import Interfaces.IInvoice;

public class AccountantInvoice implements IInvoice{
	
	private Customer customer;
	private String productName;
	private double sellingPrice;
	private double costPrice;
	
	public AccountantInvoice(Customer customer,String productName,double sellingPrice,double costPrice) {
		this.customer = customer;
		this.productName = productName;
		this.sellingPrice = sellingPrice;
		this.costPrice = costPrice;
	}

	@Override
	public void showInvoice() {
		System.out.println("------Accountant Invoice------");
		System.out.println("Customer details:");
		System.out.println(customer.toString());
		System.out.println("The product name is: " + productName);
		System.out.printf("The price is: %.2f \nThe profit is: %.2f\n",sellingPrice ,(sellingPrice - costPrice));
	}

}
