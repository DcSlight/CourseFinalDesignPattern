package Shipping;

import Components.Contact;
import Observer.ObserverManagment;

public class DHL extends ShippingCompany{
	protected final int MAX_SHIP_PRICE  = 100;
	private final double PRECENT_FEE_PRODUCT = 0.1;
	
	public DHL(Contact contact, int importTax) {
		super(contact, importTax);
	}
	
	public double calculateExpressShippingFee() {
    	return MAX_SHIP_PRICE + importTax;
    }


    public double calculateStandardShippingFee(double sellingPrice) {
    	double shippingFee=PRECENT_FEE_PRODUCT * sellingPrice;
		if(shippingFee > MAX_SHIP_PRICE)
			shippingFee = MAX_SHIP_PRICE;
		return shippingFee;
    }

	@Override
	public String getName() {
		return "DHL";
	}

	@Override
	public void update(ObserverManagment obs) {
		System.out.println("DHL get message: " + obs.getMsg());
	}

}
