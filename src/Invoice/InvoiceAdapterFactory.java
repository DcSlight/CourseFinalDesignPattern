package Invoice;

import Components.Customer;
import Interfaces.IInvoice;
import eNums.eInvoice;

public class InvoiceAdapterFactory {
	public static IInvoice createAdapterInvoice(eInvoice type,Customer customer,String productName, double sellingPrice,double costPrice) {
		switch(type) {
		case eAccountantInvoice:
			return new AccountantInvoiceAdapter(new AccountantInvoice(customer,productName,sellingPrice,costPrice));
		case eCustomerInvoice:
			return new CustomerInvoiceAdapter(new CustomerInvoice(customer,productName,sellingPrice));
		case eNone: //sold in Website
			return null;//TODO: check
		default:
			throw new IllegalArgumentException();
		}
	}
}
