package System;

import java.util.HashSet;
import java.util.Set;

import Components.Contact;
import Components.Customer;
import Exception.StockException;
import Interfaces.IShippingExpressCommand;
import Interfaces.IShippingStandardCommand;
import Products.Product;
import Products.ProductSoldInStore;
import Products.ProductSoldThroughWebsite;
import Shipping.DHLController;
import Shipping.FedExContoroller;

public class Program {

	public static void main(String[] args) {
		Set<IShippingExpressCommand> express = new HashSet<>();
		Set<IShippingStandardCommand> standard = new HashSet<>();
		Product p = new ProductSoldThroughWebsite("AA12", "Tami 4", 50, 70, 25, 4.2);
		Contact c = new Contact("idan","0506507070");
		express.add(new FedExContoroller(p, c, 200));
		express.add(new DHLController(p, c, 20));
		for(IShippingExpressCommand i : express) {
			System.out.println(i.calcExpress());
			System.out.println(i.getClass());
		}
	}

}
