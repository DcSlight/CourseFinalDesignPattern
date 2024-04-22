package Products;
import Exception.StockException;
import Order.Order;

public class ProductSoldThroughWebsite extends Product implements Cloneable{
	
	private String destCountry;

	public ProductSoldThroughWebsite(String serial, String productName, double costPrice, double sellingPrice,
			int stock,double weight,String destCountry) {
		super(serial, productName, costPrice, sellingPrice, stock,weight);
		this.destCountry = destCountry;
	}
	
	public ProductSoldThroughWebsite clone() throws CloneNotSupportedException{
		return (ProductSoldThroughWebsite) super.clone();
	}

	public String getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
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
		ProductSoldThroughWebsite p = (ProductSoldThroughWebsite)(obj);
		if(!destCountry.equals(p.getDestCountry()))
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
		st.append("Dest Country: " + destCountry);
		st.append("Total Profit: " + (float)getTotalProfit() + "$\n");
		st.append(super.toString());
		return st.toString();
	}

}
