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
import Products.Product.ProductMemento;
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
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_CYAN_BOLD = "\033[1;36m";
	public static final String ANSI_YELLOW_UNDERLINED = "\033[4;33m";
	public static final String ANSI_YELLOW_BOLD = "\033[1;33m";
	public static final String ANSI_CYAN_BRIGHT = "\033[0;96m";
	public static final String ANSI_RED_BRIGHT = "\033[0;91m"; 
	public static final String ANSI_GREEN_BRIGHT = "\033[0;92m";
	
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
		int num = 1;
		StringBuffer st = new StringBuffer();
		st.append(ANSI_CYAN + "\n--------------------------------------\n");
		st.append("\t  All Products\n");
		st.append("--------------------------------------\n" + ANSI_RESET);
		for(Product product : products) {
			st.append(ANSI_YELLOW_BOLD + "Product " + num + ":\n" + ANSI_RESET);
			st.append(product.toString());
			num++;
		}
		st.append(ANSI_CYAN_BRIGHT + "\nTotal profit in system: " + getSystemTotalProfit()+"$\n" + ANSI_RESET);
		return st.toString();
	}

	
	public void makeOrder(Product product,Customer customer, int amount,eShipType type,String destCountry) throws StockException {
		Order order;
		if(product instanceof ProductSoldThroughWebsite) {
			IShippingReceiver receiver = shippingInvoker.calculateShippingFee(type, product);
			order = new WebsiteOrder(product,customer,amount,receiver.getCompany(),type,receiver.getPrice(),destCountry);
			obs.sendProductSold(product);
			//TODO: fix the string msg of syso
			System.out.println(ANSI_YELLOW + "\n"+receiver.getCompany().getName()+" offers the cheapest shipping at $"+receiver.getPrice() + "\n" + ANSI_RESET);
		}else {
			order = new Order(product,customer,amount);
		}
		orderContorller.updateOrders(order, product);
	}//TODO: return String
	
	public String undoOrder() {
		if(orderContorller.haveOrders()) {
			orderContorller.undoOrder();
			return ANSI_GREEN_BRIGHT + "Undo has been success!\n" + ANSI_RESET;
		}
		return ANSI_RED_BRIGHT + "There are no orders to do undo\n" + ANSI_RESET;
	}
	
	public Product getProductBySerial(String serial) {
		for(Product product : products) {
			if(product.getSerial().equals(serial)) {
				return product;
			}
		}
		return null;
	}
	
	public boolean isSerialProductExist(String Serial) {
		for (Product product : products) {
			if (product.getSerial().equals(Serial))
				return true;
		}
		return false;
	}
	
	public String getProductOrders(Product product) {
		return product.getAllOrders();
	}
	
	
	public void addShippment(eShipType type,ICommand c) {
		shippingInvoker.addCommand(type,c);
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	public boolean removeProduct(Product product) {
		if(products.contains(product)) {
			this.products.remove(product);
			return true;
		}
		return false;
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
		this.companies = m.companies;
		this.products = m.products;
		for(Product product : this.products) {
			product.setMemento();
		}
		m.orderContorller.setMemento();
		this.orderContorller=m.orderContorller;
	}
	
	public static class Memento{
		private Set<Product> products;
		private Set<ShippingCompany> companies;
		private OrderController orderContorller;
		
		private Memento(Set<Product> products,Set<ShippingCompany> companies,OrderController orderContorller) {
			this.companies = new HashSet<>(companies);
			this.products = new HashSet<>();
			for(Product product : products) {
				product.createMemento();
				this.products.add(product);
			}
			orderContorller.createMemento();
			this.orderContorller = orderContorller;
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
		Product p1 = new ProductSoldThroughWebsite("AAB12", "Iphone 15 protector", 7.5,87.58 , 400, 0.25);
		Product p2 = new ProductSoldThroughWebsite("199BA", "TV", 1200,2750 , 10, 13.6);
		Product p3 = new ProductSoldThroughWebsite("78FHC", "JBL", 210.5,453.2 , 62, 1.23);
	
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
