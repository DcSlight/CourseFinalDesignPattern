package Products;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import Exception.StockException;
import Order.Order;

public abstract class Product implements Cloneable{
	protected String serial;
	protected String productName;
	protected double costPrice;
	protected double sellingPrice;
	protected int stock;
	protected double weight;
	protected Set<Order> orders;

	
	public Product(String serial, String productName, double costPrice, double sellingPrice, 
			int stock, double weight) {
		this.serial=serial;
		this.productName = productName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.stock = stock;
		this.orders = new LinkedHashSet<>();
		this.weight = weight;
	}
	
	public Product(Product p) {
		this.serial = p.getSerial();
		this.productName = p.getProductName();
		this.costPrice = p.getCostPrice();
		this.sellingPrice = p.getSellingPrice();
		this.stock = p.getStock();
		this.orders = p.getOrders();
		this.weight = p.getWeight();
	}//TODO: delete
	
	@Override
	public Product clone() throws CloneNotSupportedException{
		Product cloned = (Product) super.clone();
		cloned.orders = new LinkedHashSet<>(this.orders);
		return cloned;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void removeOrder(Order order) {
		this.orders.remove(order);
	}

	public abstract void addOrder(Order order) throws StockException ;
	
	public String getAllOrders() {
		StringBuffer st = new StringBuffer();
		if(orders.isEmpty())
			return "There are no orders!";
		for(Order o : orders) {
			st.append(o.toString());
		}
		return st.toString();		
	}

	public String getSerial() {
		return serial;
	}


	public void setSerial(String serial) {
		this.serial = serial;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}


	public double getSellingPrice() {
		return sellingPrice;
	}


	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void decreaseStock(int amount) throws StockException {
		if(amount > stock)
			throw new StockException();
		this.stock -= amount;
	}


	public Set<Order> getOrders() {
		return orders;
	}
	
	public void setOrder(Set<Order> orders) {
		this.orders=orders;
	}
	
	public double getTotalProfit() {
		double sum=0;
		for(Order order : orders) {
			sum+=order.getProfit();
		}
		return sum;
	}


	@Override
	public int hashCode() {
		return Objects.hash(serial);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if(!serial.equals(other.getSerial()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer st = new StringBuffer();
		st.append("Product serial: " + serial);
		st.append("Product Name: " + productName);
		st.append("Product Weight: " + weight + "kg");
		st.append("Current Stock: " + stock + "\n");
		st.append("Orders:\n" + getAllOrders() + "\n\n");
		return st.toString();
	}
	
}
