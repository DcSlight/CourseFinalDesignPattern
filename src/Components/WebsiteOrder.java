package Components;

import Products.Product;
import Shipping.ShippingCompany;
import eNums.eShipType;

public class WebsiteOrder extends Order{
	
	private ShippingCompany company;
	private eShipType type;
	private double shippingPrice;

	public WebsiteOrder(Product product, Customer customer, int amount,ShippingCompany company, eShipType type,double shippingPrice) {
		super(product, customer, amount);
		this.company = company;
		this.type = type;
		this.shippingPrice = shippingPrice;
	}

	public ShippingCompany getCompany() {
		return company;
	}

	public void setCompany(ShippingCompany company) {
		this.company = company;
	}

	public eShipType getType() {
		return type;
	}

	public void setType(eShipType type) {
		this.type = type;
	}

	public double getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	
	
	
}
