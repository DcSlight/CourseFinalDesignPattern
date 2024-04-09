package Components;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import Interfaces.IInvoice;
import Invoice.InvoiceAdapterFactory;
import Products.Product;
import Products.ProductSoldInStore;
import Products.ProductSoldToWholesalers;
import eNums.eInvoice;

public class Order {

	private Product product;
	private Customer customer;
	private Set<IInvoice> allInvoice;
	
	public Order(Product product, Customer customer) {
		this.product = product;
		this.customer = customer;
		this.allInvoice = new HashSet<IInvoice>();
		IInvoice invoiceAccountant = InvoiceAdapterFactory.createAdapterInvoice(eInvoice.eAccountantInvoice,
				customer, product.getProductName(),
				product.getSellingPrice(), product.getCostPrice());
		
		IInvoice invoiceCustomer = InvoiceAdapterFactory.createAdapterInvoice(eInvoice.eCustomerInvoice,
				customer, product.getProductName(),
				product.getSellingPrice(), product.getCostPrice());
		
		if(product instanceof ProductSoldInStore) {
			allInvoice.add(invoiceCustomer);
			allInvoice.add(invoiceAccountant);
		}
		else if(product instanceof ProductSoldToWholesalers) {
			allInvoice.add(invoiceAccountant);
		}
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

	public Set<IInvoice> getInvoice() {
		return allInvoice;
	}


	@Override
	public int hashCode() {
		return Objects.hash(customer, product);
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
			   && product.equals(other.product);
	}
	
	

	@Override
	public String toString() {
		StringBuffer st = new StringBuffer();
		for(IInvoice invoice : allInvoice) {
		//	st+=invoice.showInvoice(); TODO: change invoice interface to return str
		}
		return "";
	}
	
	
	
	
	
	
	
}
