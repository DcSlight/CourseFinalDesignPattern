package Shipping;

import Components.Contact;

public class FedEx extends ShippingCompany{

	private final int PRODUCT_WEIGHT = 10;
	
	public FedEx(Contact contact, double shipFee, int importTax) {
		super(contact, shipFee, importTax);
	}
}
