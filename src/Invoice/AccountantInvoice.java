package Invoice;

import Components.Customer;
import Interfaces.IInvoice;

public class AccountantInvoice implements IInvoice{
	
	private Customer customer;
	private String productName;
	private int sellingPrice;
	private int costPrice;
	
	public AccountantInvoice(Customer customer,String productName,int sellingPrice,int costPrice) {
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
		System.out.println("The selling price is: " + sellingPrice + " The profit is: " + (sellingPrice - costPrice));
	}

}
