package System;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import Components.Contact;
import Components.Customer;
import Components.Order;
import Components.WebsiteOrder;
import Exception.StockException;
import Interfaces.ICommand;
import Interfaces.IShippingReceiver;
import Observer.ObserverManagment;
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
	
	private SystemFacade() {
		this.products = new HashSet<>();
		this.shippingInvoker = new ShippingInvoker();
		this.companies = new HashSet<>();
		initCompanies();
		initProducts();//according to the assignment - hard coded 9 products
		obs = new ObserverManagment();
		initObservers();
	}
	
	private void initObservers() {
		for(ShippingCompany company : companies) {
			obs.attach(company);
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
	
	public void makeOrder(Product product,Customer customer, int amount) throws StockException {
		Order order;
		if(product instanceof ProductSoldThroughWebsite) {
			eShipType type=getShipTypeMenu();
			IShippingReceiver receiver = shippingInvoker.calculateShippingFee(type, product);
			order = new WebsiteOrder(product,customer,amount,receiver.getCompany(),type,receiver.getPrice());
			System.out.println(receiver.getCompany().getName()+" offers the cheapest shipping at $"+receiver.getPrice());
			obs.click();
		}else {
			order = new Order(product,customer,amount);
		}
		product.addOrder(order);
	}
	
	public Product getProductBySerial(String serial) {
		for(Product product : products) {
			if(product.getSerial().equals(serial)) {
				return product;
			}
		}
		return null;
	}
	
	private eShipType getShipTypeMenu() {
		Scanner sc = new Scanner(System.in);
		eShipType type=eShipType.eNone;
		int tmp;
		boolean flag=true;
		do {
			System.out.println("Please pick the shipping type");
			System.out.println("For Express enter 1");
			System.out.println("For Standard enter 2");
			tmp = sc.nextInt();
			switch(tmp) {
			case 1:
				type=eShipType.eExpress;
				flag = false;
				break;
			case 2:
				type=eShipType.eStandard;
				flag = false;
				break;
			default:
				flag = true;
				break;
			}
		}while(flag);
		sc.close();
		return type;
	}
	
	public void addShippment(eShipType type,ICommand c) {
		shippingInvoker.addCommand(type,c);
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
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
}
