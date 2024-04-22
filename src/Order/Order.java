package Order;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import Components.Customer;
import Interfaces.IInvoice;
import Invoice.InvoiceAdapterFactory;
import Products.Product;
import Products.ProductSoldInStore;
import Products.ProductSoldToWholesalers;
import eNums.eInvoice;

public class Order implements Cloneable{
	private int id;//TODO: change to String 4.5
	private static int counter = 0;
	private int amount;
	private double profit;
	private Product product;
	private Customer customer;
	private Set<IInvoice> allInvoice;
	
	public Order(Product product, Customer customer,int amount) {
		this.product = product;
		this.customer = customer;
		this.amount = amount;
		this.profit  = (product.getSellingPrice() - product.getCostPrice()) * amount;
		this.allInvoice = new HashSet<IInvoice>();
		initInvoice();
		counter++;
		this.id=counter;
	}
	
	@Override
	public Order clone() throws CloneNotSupportedException{
		Order cloned = (Order) super.clone();
		cloned.product = product;
		cloned.customer = customer.clone();
		cloned.allInvoice = new HashSet<>(this.allInvoice);
		return cloned;
	}
	
	private void initInvoice() {
		IInvoice invoiceAccountant = InvoiceAdapterFactory.createAdapterInvoice(eInvoice.eAccountantInvoice,
				customer,product ,amount);
		
		IInvoice invoiceCustomer = InvoiceAdapterFactory.createAdapterInvoice(eInvoice.eCustomerInvoice,
				customer, product,amount);
		
		if(product instanceof ProductSoldInStore) {
			allInvoice.add(invoiceCustomer);
			allInvoice.add(invoiceAccountant);
		}
		else if(product instanceof ProductSoldToWholesalers) {
			allInvoice.add(invoiceAccountant);
		}		
	}
	
	public int getAmount() {
		return amount;
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
		return Objects.hash(id);
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
	
	public double getProfit() {
		return profit;
	}

	@Override
	public String toString() {
		StringBuffer st = new StringBuffer();
		for(IInvoice invoice : allInvoice) {
			st.append(invoice.showInvoice());
		}
		return st.toString();
	}
	
	
	
	
	
	
	
}
