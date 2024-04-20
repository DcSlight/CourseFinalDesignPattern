package Products;

import Components.Order;
import Exception.StockException;

public class ProductSoldToWholesalers extends Product{

	public ProductSoldToWholesalers(String serial, String productName, double costPrice, double sellingPrice, int stock,double weight) {
		super(serial, productName, costPrice, sellingPrice, stock,weight);
	}

	@Override
	public void addOrder(Order order) throws StockException {
		decreaseStock(order.getAmount());
		//TODO: check if need to throw error for type 
		orders.add(order);
	}

}
