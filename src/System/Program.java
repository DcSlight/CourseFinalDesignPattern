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
		SystemFacade systemFacade = SystemFacade.getInstance();
		Customer c = new Customer("idan", "05");
		Set<Product> products = systemFacade.getProducts();
		Product p1 = systemFacade.getProductBySerial("78FHC");
		System.out.println(p1);
		systemFacade.makeOrder(p1, c, 5);
		System.out.println(p1);
	}

}
