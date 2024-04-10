package Shipping;

import Components.Contact;
import Interfaces.IShippingExpress;

public class DHLExpress extends DHL implements IShippingExpress{

	public DHLExpress(Contact contact, double shipFee, int importTax) {
		super(contact, shipFee, importTax);
	}

	@Override
	public int calcShippingFee() {
		// TODO FILL
		return 0;
	}

}
