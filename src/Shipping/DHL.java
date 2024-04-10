package Shipping;

import Components.Contact;

public class DHL extends ShippingCompany{
	private final int MAX_SHIP_PRICE  = 100;
	
	public DHL(Contact contact, double shipFee, int importTax) {
		super(contact, shipFee, importTax);
	}
	
	
}
