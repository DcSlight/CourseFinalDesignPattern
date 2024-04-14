package Shipping;

import Components.Contact;
import Interfaces.IShippingCommand;

public class DHLExpressCommand extends DHL implements IShippingCommand{

	public DHLExpressCommand(Contact contact, int importTax) {
		super(contact, importTax);
	}

	@Override
	public double execute() {
		return MAX_SHIP_PRICE + importTax;
	}
}
