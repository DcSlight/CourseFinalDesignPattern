package Products;

import Components.Customer;
import Components.Order;

public class ProductSoldToWholesalers extends Product{

	public ProductSoldToWholesalers(String serial, String productName, double costPrice, double sellingPrice, int stock) {
		super(serial, productName, costPrice, sellingPrice, stock);
	}

	@Override
	public void addOrder(Customer customer,int amount) {
		decreaseStock(amount);
		Order order = new Order(this, customer);
		orders.add(order);
	}

}
