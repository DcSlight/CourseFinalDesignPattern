package System;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import Components.Contact;
import Components.Customer;
import Exception.StockException;
import Interfaces.ICommand;
import Interfaces.IShippingReceiver;
import Observer.ObserverManagment;
import Order.Order;
import Order.OrderController;
import Order.WebsiteOrder;
import Products.Product;
import Products.ProductSoldInStore;
import Products.ProductSoldThroughWebsite;
import Products.ProductSoldToWholesalers;
import Shipping.DHL;
import Shipping.FedEx;
import Shipping.ShippingCompany;
import Shipping.ShippingFactory;
import Shipping.ShippingInvoker;
import eNums.eShipType;


public class SystemFacade {
	private static SystemFacade instance;
	private Set<Product> products;
	private ShippingInvoker shippingInvoker;
	private Set<ShippingCompany> companies;
	private ObserverManagment obs;
	private OrderController orderContorller;
	private Memento memento;
	
	private SystemFacade() {
		this.products = new HashSet<>();
		this.shippingInvoker = new ShippingInvoker();
		this.companies = new HashSet<>();
		this.orderContorller = new OrderController();
		initCompanies();
		initProducts();//according to the assignment - hard coded 9 products
		obs = new ObserverManagment();
		initObservers();
		backUpSystem();
	}
	
	private void initObservers() {
		for(ShippingCompany company : companies) {
			obs.attach(company);
		}
	}
	
	public double getSystemTotalProfit() {
		double sum=0;
		for(Product product : products) {
			sum+=product.getTotalProfit();
		}
		return sum;
	}
	
	public String getAllProducts() {
		StringBuffer st = new StringBuffer();
		st.append("Total profit in system: " + getSystemTotalProfit()+"$\n");
		for(Product product : products) {
			st.append(product.toString());
		}
		return st.toString();
	}

	
	public void makeOrder(Product product,Customer customer, int amount,eShipType type) throws StockException {
		Order order;
		if(product instanceof ProductSoldThroughWebsite) {
			IShippingReceiver receiver = shippingInvoker.calculateShippingFee(type, product);
			order = new WebsiteOrder(product,customer,amount,receiver.getCompany(),type,receiver.getPrice());
			obs.sendProductSold(product);
			//TODO: fix the string msg of syso
			System.out.println("\n"+receiver.getCompany().getName()+" offers the cheapest shipping at $"+receiver.getPrice());
		}else {
			order = new Order(product,customer,amount);
		}
		orderContorller.updateOrders(order, product);
	}//TODO: return String
	
	public String undoOrder() {
		if(orderContorller.haveOrders()) {
			orderContorller.undoOrder();
			return "Undo has been success!\n";
		}
		return "There are no orders to do undo\n";
	}
	
	public Product getProductBySerial(String serial) {
		for(Product product : products) {
			if(product.getSerial().equals(serial)) {
				return product;
			}
		}
		return null;
	}
	
	
	
	public void addShippment(eShipType type,ICommand c) {
		shippingInvoker.addCommand(type,c);
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	public void removeProduct(Product product) {
		this.products.remove(product);
	}
	
	public Set<Product> getProducts(){
		return this.products;
	}
	
	public Set<ShippingCompany> getCompanies(){
		return this.companies;
	}
	
	
	public void addCompany(ShippingCompany c) {
		companies.add(c);
	}
	
	public static SystemFacade getInstance() {
		if (instance == null) {
            instance = new SystemFacade();
        }
        return instance;
	}
	
	
	public void backUpSystem() {
		memento = createMemento();
	}
	
	public void restoreSystem() {
		setMemento(this.memento);
	}
	
	private Memento createMemento() {
		return new Memento(products,companies,orderContorller);
	}
	
	private void setMemento(Memento m) {
		this.products = m.products;
		this.companies = m.companies;
		this.orderContorller = m.orderContorller;
	}
	
	public static class Memento{
		
		private Set<Product> products;
		private ShippingInvoker shippingInvoker;
		private Set<ShippingCompany> companies;
		private ObserverManagment obs;
		private OrderController orderContorller;
		
		private Memento(Set<Product> products,Set<ShippingCompany> companies,OrderController orderContorller) {
			this.products = new HashSet<>();
			this.companies = new HashSet<>();
			try { 
				for(Product product: products) 
					this.products.add(product.clone());
				for(ShippingCompany company : companies) 
						this.companies.add(company.clone());
				this.orderContorller = orderContorller.clone();//TODO: fix
			} catch (CloneNotSupportedException e) {
			}
		}
	}
	
	private void initCompanies() {
		Contact contactDHL = new Contact("Yossi-DHL","0522801897");
		ShippingCompany dhl = new DHL(contactDHL,20);
		addCompany(dhl);
		ICommand dhlExpress = ShippingFactory.createShippingCommand(eShipType.eExpress, dhl);
		addShippment(eShipType.eExpress, dhlExpress);
		ICommand dhlStandard = ShippingFactory.createShippingCommand(eShipType.eStandard, dhl);
		addShippment(eShipType.eStandard,dhlStandard);
		
		Contact contactFedEx = new Contact("Roy-FedEx","0522206896");
		ShippingCompany fedEx = new FedEx(contactFedEx,70);
		addCompany(fedEx);
		ICommand fedExExpress = ShippingFactory.createShippingCommand(eShipType.eExpress, fedEx);
		addShippment(eShipType.eExpress,fedExExpress);
		ICommand fedExStandard = ShippingFactory.createShippingCommand(eShipType.eStandard, fedEx);
		addShippment(eShipType.eStandard,fedExStandard);
	}
	
	private void initProducts() {
		Product p1 = new ProductSoldThroughWebsite("AAB12", "Iphone 15 protector", 7.5,87.58 , 400, 0.25,"Israel");
		Product p2 = new ProductSoldThroughWebsite("199BA", "TV", 1200,2750 , 10, 13.6,"USA");
		Product p3 = new ProductSoldThroughWebsite("78FHC", "JBL", 210.5,453.2 , 62, 1.23,"Germany");
	
		Product p4 = new ProductSoldInStore("AHDHB2", "Battery", 12.5, 25, 20, 0.2);
		Product p5 = new ProductSoldInStore("AFCHP7", "Coat", 45.6, 350, 62, 1.7);
		Product p6 = new ProductSoldInStore("PDKSU2", "T-Shirt", 12.5,98, 158, 1.1);
		
		Product p7 = new ProductSoldToWholesalers("P3MCJU", "Coca Cola", 0.5, 10, 2500, 0.6);
		Product p8 = new ProductSoldToWholesalers("MXJQXT", "Mayo", 15, 17, 1230, 5);
		Product p9 = new ProductSoldToWholesalers("MPXL2K", "Toilet Paper", 5, 45, 5800, 3);
		addProduct(p1);
		addProduct(p2);
		addProduct(p3);
		addProduct(p4);
		addProduct(p5);
		addProduct(p6);
		addProduct(p7);
		addProduct(p8);
		addProduct(p9);		
	}
}
