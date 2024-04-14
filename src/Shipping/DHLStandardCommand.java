package Shipping;

import Components.Contact;
import Interfaces.IShippingCommand;

public class DHLStandardCommand extends DHL implements IShippingCommand{
	
	private final double PRECENT_FEE_PRODUCT = 0.1;
	protected double price;

	public DHLStandardCommand(Contact contact, int importTax,double price) {
		super(contact, importTax);
		this.price = price;
		
	}

	@Override
	public double execute() {
		double shippingFee=PRECENT_FEE_PRODUCT * this.price;
		if(shippingFee > MAX_SHIP_PRICE)
			shippingFee = MAX_SHIP_PRICE;
		return shippingFee;
		
	}
	
}
