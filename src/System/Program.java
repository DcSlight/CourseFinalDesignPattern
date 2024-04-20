package System;

import java.util.Set;

import Components.Contact;
import Components.Customer;
import Components.Order;
import Exception.StockException;
import Products.Product;
import Products.ProductSoldThroughWebsite;
import Shipping.*;
import eNums.eShipType;

public class Program {

	public static void main(String[] args) throws Exception  {
		SystemFacade system = SystemFacade.getInstance();
		Set<Product> p = system.getProducts();
		Product p2=null;
		for(Product p1 : p) {
			p2=p1;
			break;
		}
		Customer c1 = new Customer("idan","0506004040");
		System.out.println(p2);
		try {
			system.makeOrder(p2, c1, 160);
		}
		catch(Exception e) {
			throw e;//TODO:
		}
		System.out.println(p2);
	}

}
