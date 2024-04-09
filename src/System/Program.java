package System;

import Components.Customer;
import Interfaces.IInvoice;
import Invoice.InvoiceAdapterFactory;
import eNums.eInvoice;

public class Program {

	public static void main(String[] args) {
		eInvoice customerInvoice = eInvoice.eCustomerInvoice;
		eInvoice accountant = eInvoice.eAccountantInvoice;
		Customer customer = new Customer("idan","0524854846");
		IInvoice i1=InvoiceAdapterFactory.createAdapterInvoice(customerInvoice,customer,"Tami 4",60,50);
		IInvoice i2=InvoiceAdapterFactory.createAdapterInvoice(accountant, customer, "Yoel geva", 60, 50);
		i1.showInvoice();
		i2.showInvoice();
	}

}
