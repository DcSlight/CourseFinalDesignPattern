package Products;

import Components.Customer;
import Components.Order;
import Exception.StockException;
import eNums.eShipType;

public class ProductSoldInStore extends Product{
	
	final int DOLLAR_TO_SHEKEL=4;
	
	
	public ProductSoldInStore(String serial, String productName, double costPrice, double sellingPrice, int stock,double weight) {
		super(serial, productName, costPrice, sellingPrice, stock,weight);
	}


	@Override
	public void addOrder(Order order) throws StockException {
		decreaseStock(order.getAmount());
		//TODO: check if need to throw error for type 
		orders.add(order);		
	}




	
	


}
