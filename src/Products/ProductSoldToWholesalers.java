package Products;

import Exception.StockException;
import Order.Order;

public class ProductSoldToWholesalers extends Product{

	public ProductSoldToWholesalers(String serial, String productName, double costPrice, double sellingPrice, int stock,double weight) {
		super(serial, productName, costPrice, sellingPrice, stock,weight);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	

	@Override
	public void addOrder(Order order) throws StockException {
		decreaseStock(order.getAmount());
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
