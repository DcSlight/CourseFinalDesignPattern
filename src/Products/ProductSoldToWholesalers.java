package Products;

import Exception.StockException;
import Order.Order;

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
	
	@Override
	public String toString() {
		StringBuffer st = new StringBuffer();
		st.append("Product type: " + this.getClass().getSimpleName() + "\n");
		st.append("Total Profit: " + (float)getTotalProfit() + "$\n");
		st.append(super.toString());
		return st.toString();
	}

}
