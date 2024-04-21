package Products;

import java.util.Scanner;

import Components.Customer;
import Exception.StockException;
import Order.Order;
import Order.WebsiteOrder;
import eNums.eShipType;

public class ProductSoldThroughWebsite extends Product{

	public ProductSoldThroughWebsite(String serial, String productName, double costPrice, double sellingPrice,
			int stock,double weight) {
		super(serial, productName, costPrice, sellingPrice, stock,weight);
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


	public void addOrder(Order order) throws StockException {
		decreaseStock(order.getAmount());
		orders.add(order);
	}

}
