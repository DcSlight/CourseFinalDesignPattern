package Invoice;

import Components.Customer;
import Interfaces.IInvoice;

public class AccountantInvoice implements IInvoice{
	
	private Customer customer;
	private String productName;
	private double sellingPrice;
	private double costPrice;
	private char currency;
	private int amount;
	
	public AccountantInvoice(Customer customer,String productName,double sellingPrice,double costPrice,int amount,char currency) {
		this.customer = customer;
		this.productName = productName;
		this.sellingPrice = sellingPrice;
		this.costPrice = costPrice;
		this.amount = amount;
		this.currency = currency;
	}
	
	public double getProfit() {
		return (sellingPrice - costPrice)*amount;
	}
	
	public double price() {
		return sellingPrice * amount;
	}

	@Override
	public String showInvoice() {
		double profit = getProfit();
		double price = price();
		StringBuffer st = new StringBuffer();
		st.append("\n------Accountant Invoice------\n");
		st.append("Customer details:\n");
		st.append(customer.toString());
		st.append("\nThe product name is: " + productName);
		st.append("\nThe price is: "+(price)+currency + "\nThe profit is: "+ String.format("%.2f",profit)+currency);
		return st.toString();
	}

}
