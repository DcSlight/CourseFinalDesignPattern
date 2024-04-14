package Shipping;

import Components.Contact;

public class DHL extends ShippingCompany{
	protected final int MAX_SHIP_PRICE  = 100;
	
	public DHL(Contact contact, int importTax) {
		super(contact, importTax);
	}
	
	
}
