package Shipping;

import Components.Contact;
import Interfaces.IShippingCommand;

public class DHLStandardCommand extends DHL implements IShippingCommand{

	public DHLStandardCommand(Contact contact, double shipFee, int importTax) {
		super(contact, shipFee, importTax);
	}

	@Override
	public double execute() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
