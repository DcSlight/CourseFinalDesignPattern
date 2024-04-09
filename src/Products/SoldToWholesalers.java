package Products;

import Components.Order;

public class SoldToWholesalers extends Product{

	public SoldToWholesalers(String serial, String productName, double costPrice, double sellingPrice, int stock) {
		super(serial, productName, costPrice, sellingPrice, stock);
	}

	@Override
	public void addOrder(Order order) {
		//TODO: fill
	}

}
