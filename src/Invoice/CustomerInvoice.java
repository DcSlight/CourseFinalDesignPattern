package Invoice;

import Components.Customer;
import Interfaces.IInvoice;

public class CustomerInvoice implements IInvoice {
	final double VAT = 0.17;
	private Customer customer;
	private String productName;
	private int sellingPrice;	
	
	public CustomerInvoice(Customer customer,String productName, int sellingPrice) {
		this.customer = customer;
		this.productName = productName;
		this.sellingPrice = sellingPrice;
	}

	@Override
	public void showInvoice() {
		System.out.println("------Customer Invoice------");
		System.out.println("Customer details:");
		System.out.println(customer.toString());
		System.out.println("The product name is: " + productName);
		System.out.printf("The price is: %d \nVAT is: %.2f\n",sellingPrice ,sellingPrice*VAT);
	}
	
}
