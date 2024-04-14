package Shipping;

import Components.Contact;

public class FedEx extends ShippingCompany{

	protected final int PRODUCT_WEIGHT = 10;
	
	public FedEx(Contact contact,int importTax) {
		super(contact,importTax);
	}
}
