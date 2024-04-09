package Products;

import Components.Order;

public class ProductSoldThroughWebsite extends Product{

	public ProductSoldThroughWebsite(String serial, String productName, double costPrice, double sellingPrice,
			int stock) {
		super(serial, productName, costPrice, sellingPrice, stock);
	}

	@Override
	public void addOrder(Order order) {
		// TODO: fill		
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
	
	@Override
	public String toString()  {
		return super.toString();
	}//TODO: need to update

}
