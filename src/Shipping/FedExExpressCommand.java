package Shipping;

import Components.Contact;
import Interfaces.IShippingCommand;

public class FedExExpressCommand extends FedEx implements IShippingCommand{
	private double weight;
	private final int FEE_EXPRESS = 50;

	public FedExExpressCommand(Contact contact, int importTax,double weight) {
		super(contact, importTax);
		this.weight = weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public double execute() {
		return (weight / PRODUCT_WEIGHT)*FEE_EXPRESS + importTax;
	}

}
