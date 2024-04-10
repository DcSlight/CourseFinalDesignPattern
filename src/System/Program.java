package System;

import Components.Customer;
import Exception.StockException;
import Products.Product;
import Products.ProductSoldInStore;

public class Program {

	public static void main(String[] args) {
		//eInvoice customerInvoice = eInvoice.eCustomerInvoice;
		//eInvoice accountant = eInvoice.eAccountantInvoice;
		Customer customer1 = new Customer("idan","0524854846");
		Customer customer2= new Customer("sapir","0524854846");
		Customer customer3 = new Customer("roi","0524854846");
		//IInvoice i1=InvoiceAdapterFactory.createAdapterInvoice(customerInvoice,customer,"Tami 4",60,50);
	//	IInvoice i2=InvoiceAdapterFactory.createAdapterInvoice(accountant, customer, "Yoel geva", 60, 50);
		//System.out.println(i1.showInvoice());
		//System.out.println(i2.showInvoice());	
		
		
		Product product = new ProductSoldInStore("AABZO38734", "ODD TAVIM", 30, 150, 25);
		try {
			product.addOrder(customer1, 15);
			product.addOrder(customer2, 2);
			product.addOrder(customer3, 3);
		}catch(StockException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(product.getAllOrders());
	}

}
