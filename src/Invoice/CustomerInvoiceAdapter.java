package Invoice;

import Interfaces.IInvoice;

public class CustomerInvoiceAdapter implements IInvoice{
	private CustomerInvoice adaptee;
	
	public CustomerInvoiceAdapter(CustomerInvoice customerInvoice) {
		this.adaptee = customerInvoice;
	}

	@Override
	public void showInvoice() {
		adaptee.showInvoice();		
	}
}
