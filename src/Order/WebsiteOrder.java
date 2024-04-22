package Order;

import Components.Customer;
import Products.Product;
import Shipping.ShippingCompany;
import eNums.eShipType;

public class WebsiteOrder extends Order implements Cloneable{
	
	private ShippingCompany company;
	private eShipType type;
	private double shippingPrice;

	public WebsiteOrder(Product product, Customer customer, int amount,ShippingCompany company, eShipType type,double shippingPrice) {
		super(product, customer, amount);
		this.company = company;
		this.type = type;
		this.shippingPrice = shippingPrice;
	}
	
	public WebsiteOrder clone() throws CloneNotSupportedException{
		WebsiteOrder cloned = (WebsiteOrder) super.clone();
		cloned.company = this.company.clone(); // Deep copy
        return cloned;
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
	
	public String toString() {
		StringBuffer st = new StringBuffer();
		st.append(super.toString());
		st.append(company + "\ntype: " + type.name() + "\nshipping price: " + shippingPrice );
		return st.toString();
	}
	
	
	
}
