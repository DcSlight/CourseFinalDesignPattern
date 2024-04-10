package Shipping;

import Components.Contact;
import Interfaces.IShippingStandard;

public class FedExStandard extends FedEx implements IShippingStandard{
	
	private final int FEE_STANDARD = 10;

	public FedExStandard(Contact contact, double shipFee, int importTax) {
		super(contact, shipFee, importTax);
	}

	@Override
	public int calcShippingFee() {
		return 0;//TODO
	}

}
