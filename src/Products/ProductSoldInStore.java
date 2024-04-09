package Products;

import Components.Customer;
import Components.Order;
import Interfaces.IInvoice;
import Invoice.InvoiceAdapterFactory;
import eNums.eInvoice;

public class ProductSoldInStore extends Product{
	
	final int DOLLAR_TO_SHEKEL=4;
	
	
	public ProductSoldInStore(String serial, String productName, double costPrice, double sellingPrice, int stock) {
		super(serial, productName, costPrice, sellingPrice, stock);
	}


	@Override
	public void addOrder(Customer customer, int amount) {
		decreaseStock(amount);
		Order order = new Order(this, customer);
		orders.add(order);		
	}




	
	


}
