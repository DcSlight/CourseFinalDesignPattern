package Products;
import Exception.StockException;
import Order.Order;

public class ProductSoldThroughWebsite extends Product{

	public ProductSoldThroughWebsite(String serial, String productName, double costPrice, double sellingPrice,
			int stock,double weight) {
		super(serial, productName, costPrice, sellingPrice, stock,weight);
	}
	
	public ProductSoldThroughWebsite clone() throws CloneNotSupportedException{
		return (ProductSoldThroughWebsite) super.clone();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}//TODO: need to update

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}//TODO: need to update


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
