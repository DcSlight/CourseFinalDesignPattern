package Components;

import java.util.Objects;

import Interfaces.IInvoice;
import Products.Product;

public class Order {

	private String serial;
	private Product product;
	private Customer customer;
	private IInvoice invoice;
	
	public Order(String serial, Product product, Customer customer, IInvoice invoice) {
		this.serial = serial;
		this.product = product;
		this.customer = customer;
		this.invoice = invoice;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public IInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(IInvoice invoice) {
		this.invoice = invoice;
	}


	@Override
	public int hashCode() {
		return Objects.hash(customer, product, serial);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return customer.equals(other.customer)
			   && product.equals(other.product)
			   && serial.equals(other.serial);
	}
	
	

	@Override
	public String toString() {
		return "Order [serial=" + serial + ", product=" + product + ", customer=" + customer + ", invoice=" + invoice
				+ "]";
	}
	
	
	
	
	
	
	
}
