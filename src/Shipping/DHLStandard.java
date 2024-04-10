package Shipping;

import Components.Contact;
import Interfaces.IShippingStandard;

public class DHLStandard extends DHL implements IShippingStandard{

	public DHLStandard(Contact contact, double shipFee, int importTax) {
		super(contact, shipFee, importTax);
	}

	@Override
	public int calcShippingFee() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
