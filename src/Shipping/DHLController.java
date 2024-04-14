package Shipping;

import Components.Contact;
import Interfaces.IShippingExpressCommand;
import Interfaces.IShippingStandardCommand;
import Products.Product;

public class DHLController implements IShippingStandardCommand,IShippingExpressCommand{
	private DHLStandardCommand DHLStandard;
	private DHLExpressCommand DHLExpress;
	
	public DHLController(Product product,Contact contact,int importTax ) {
		this.DHLStandard = new DHLStandardCommand(contact, importTax,product.getSellingPrice());
		this.DHLExpress = new DHLExpressCommand(contact, importTax);
	}
	
	public double calcExpress() {
		return DHLExpress.execute();
	}
	
	public double calcStandard() {
		return DHLStandard.execute();
	}
}
