package Shipping;

import Components.Contact;
import Interfaces.IShippingExpress;

public class FedExExpress extends FedEx implements IShippingExpress{

	public FedExExpress(Contact contact, double shipFee, int importTax) {
		super(contact, shipFee, importTax);
	}

	@Override
	public int calcShippingFee() {
		// TODO Auto-generated method stub
		return 0;
	}

}
