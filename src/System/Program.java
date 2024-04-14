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
import Shipping.DHL;
import Shipping.DHLController;
import Shipping.FedEx;
import Shipping.FedExContoroller;
import Shipping.ShippingCompany;

public class Program {

	public static void main(String[] args) {
		Set<IShippingExpressCommand> express = new HashSet<>();
		Set<IShippingStandardCommand> standard = new HashSet<>();
		Product p = new ProductSoldThroughWebsite("AA12", "Tami 4", 50, 70, 25, 4.2);
		Contact c = new Contact("idan","0506507070");
		express.add(new FedExContoroller(p, c, 200));
		express.add(new DHLController(p, c, 20));
		double min = Double.MAX_VALUE;
		double tmp;
		String company="";
		for(IShippingExpressCommand i : express) {
			tmp = i.calcExpress();
			if(min > tmp){
				min = tmp;
				company = i.toString();
			}
		}
		System.out.println(company + "\nThe shipping fee is " + min + "\nThe product cost is: "+p.getSellingPrice() +"\nThe ship type");
		
		/*
		 * Set<ShippingCompany> companies = new HashSet<>(); companies.add(new
		 * DHL(c,200)); companies.add(new FedEx(c,50));
		 */
	}

}
