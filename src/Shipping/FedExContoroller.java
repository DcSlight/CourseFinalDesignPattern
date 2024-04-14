package Shipping;

import Components.Contact;
import Interfaces.IShippingCommand;
import Interfaces.IShippingExpressCommand;
import Interfaces.IShippingStandardCommand;
import Products.Product;

public class FedExContoroller implements IShippingStandardCommand,IShippingExpressCommand{
	
	private FedExStandardCommand fedExStandard;
	private FedExExpressCommand fedExExpress;
	
	public FedExContoroller(Product product,Contact contact,int importTax ) {
		this.fedExStandard = new FedExStandardCommand(contact, importTax,product.getWeight());
		this.fedExExpress = new FedExExpressCommand(contact, importTax,product.getWeight());
	}
	
	public double calcExpress() {
		return fedExExpress.execute();
	}
	
	public double calcStandard() {
		return fedExStandard.execute();
	}
}