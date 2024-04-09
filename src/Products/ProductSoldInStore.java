package Products;

import Components.Order;

public class ProductSoldInStore extends Product{
	
	final int DOLLAR_TO_SHEKEL=4;
	
	
	public ProductSoldInStore(String serial, String productName, double costPrice, double sellingPrice, int stock) {
		super(serial, productName, costPrice, sellingPrice, stock);
	}


	@Override
	public void addOrder(Order order) {
		//TODO: fill
	}

	
	


}
