package Shipping;

import Components.Contact;
import Interfaces.IShippingCommand;

public class FedExStandardCommand extends FedEx implements IShippingCommand{
	
	private final int FEE_STANDARD = 10;
	private double weight;
	
	
	public FedExStandardCommand(Contact contact, int importTax,double weight) {
		super(contact, importTax);
		this.weight = weight;
	}

	public double execute() {
		return (weight / PRODUCT_WEIGHT)*FEE_STANDARD;
	}

}
