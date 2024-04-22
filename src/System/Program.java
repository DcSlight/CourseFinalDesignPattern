package System;

import java.util.InputMismatchException;
import java.util.Scanner;
import Components.Customer;
import Exception.StockException;
import Products.Product;
import Products.ProductFactory;
import Products.ProductSoldThroughWebsite;
import eNums.eProduct;
import eNums.eShipType;

public class Program {
	
	public static final String ADD_PRODUCT = "1";
	public static final String REMOVE_PRODUCT = "2";
	public static final String EDIT_PRODUCT_STOCK = "3";
	public static final String ADD_ORDER = "4";
	public static final String UNDO_ORDER = "5";
	public static final String PRINT_PRODUCT_DETAILS = "6";
	public static final String PRINT_ALL_PRODUCTS = "7";
	public static final String PRINT_PRODUCT_ORDERS = "8";
	public static final String BACKUP_SYSTEM = "9";
	public static final String RESTORE_SYSTEM = "10";
	public static final String EXIT_1 = "E";
	public static final String EXIT_2 = "e";
	public static final Boolean POSITIVE = true;

	public static void main(String[] args) throws Exception  {
		Scanner sc = new Scanner(System.in);
		SystemFacade systemFacade = SystemFacade.getInstance();
		boolean flag = true;
		String option;
		System.out.println("--------------------------------------");
		System.out.println("\tWelcome to the System!");
		System.out.println("--------------------------------------");
		do {
			System.out.println("1 - To add a product");
			System.out.println("2 - To remove a product");
			System.out.println("3 - To edit a product stock");
			System.out.println("4 - To add a order to product");
			System.out.println("5 - To undo an order");
			System.out.println("6 - Print a product details");
			System.out.println("7 - Print system profit and all products");
			System.out.println("8 - Print product orders");
			System.out.println("9 - To backup System");
			System.out.println("10 - To restore System");
			System.out.println("E/e - To Exit");
			option = sc.nextLine();
			switch(option) {
			case ADD_PRODUCT:
				addProductMenu(sc,systemFacade);
				break;
			case REMOVE_PRODUCT:
				removeProduct(sc,systemFacade);
				break;
			case EDIT_PRODUCT_STOCK:
				editProductStock(sc,systemFacade);
				break;
			case ADD_ORDER:
				addOrderMenu(sc,systemFacade);
				break;
			case UNDO_ORDER:
				systemFacade.undoOrder();
				break;
			case PRINT_PRODUCT_DETAILS:
				printSpecificProduct(sc,systemFacade);
				break;
			case PRINT_ALL_PRODUCTS:
				System.out.println(systemFacade.getAllProducts());
				break;
			case PRINT_PRODUCT_ORDERS:
				break;
			case BACKUP_SYSTEM:
				break;
			case RESTORE_SYSTEM:
				break;
			case EXIT_1:
			case EXIT_2:
				System.out.println("Have a good day");
				flag=false;
				break;
			default:
				System.out.println("Invalid Input");
				break;
			}
			
		}while(flag);
	}
	
	public static void addOrderMenu(Scanner sc, SystemFacade systemFacade) {
		Product product;
		int amount;
		Customer customer =getCustomerDetailsMenu(sc);
		eShipType type;
		product=getProductBySerial(sc,systemFacade);
		if(product == null){
			System.out.println("Product was not found!");
			return;
		}
		if(product instanceof ProductSoldThroughWebsite)
			type = getShipTypeMenu(sc);
		else
			type = eShipType.eNone;
		amount = (int) getValidNumber(sc,"How many of this product so you want to order?\n", POSITIVE, Integer.class);
		try {
			systemFacade.makeOrder(product, customer, amount, type);
		}catch(StockException e ) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void printSpecificProduct(Scanner sc, SystemFacade systemFacade) {
		Product product;
		product=getProductBySerial(sc,systemFacade);
		if(product == null){
			System.out.println("Product was not found!");
			return;
		}
		System.out.println(product);
		
	}
	
	public static void removeProduct(Scanner sc, SystemFacade systemFacade)
	{
		Product product;
		product = getProductBySerial(sc, systemFacade);
		systemFacade.removeProduct(product);
	}
	
	public static void editProductStock(Scanner sc, SystemFacade systemFacade)
	{
		Product product;
		int stock;
		product = getProductBySerial(sc, systemFacade);
		stock = (int) getValidNumber(sc, "Enter a stock number for the product\n", POSITIVE, Integer.class);
		product.setStock(stock);
	}
	
	public static Product getProductBySerial(Scanner sc,SystemFacade systemFacade) {
		Product product;
		String serial;
		System.out.println("Please Enter serial number");
		serial = sc.nextLine();
		product=systemFacade.getProductBySerial(serial);
		return product;
	}
	
	
	public static <T extends Number> Number getValidNumber(Scanner scanner, String prompt, boolean needToBePositive, Class<T> type) {
		Number tmp = null;
		while (true) {
            System.out.print(prompt);
            try {
            	if (type == Integer.class) {
            		tmp = scanner.nextInt();
            	}
            	else if (type == Double.class) {
            		tmp = scanner.nextDouble();
            	}                
                if(needToBePositive) {
                	if(tmp.doubleValue() > 0) {
                		scanner.nextLine();//clean buffer
                		return tmp;
                	}
                	throw  new InputMismatchException();
                }
                scanner.nextLine();//clean buffer
                return tmp;	
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine();//clean buffer
            }
        }
    }

	public static Customer getCustomerDetailsMenu(Scanner sc) {
		Customer c;
		String name,mobile;
		System.out.println("Enter Customer name");
		name= sc.nextLine();
		System.out.println("Enter phone number");
		mobile = sc.nextLine();
		c = new Customer(name,mobile);
		return c;
	}
	
	public static void addProductMenu(Scanner sc,SystemFacade systemFacade) {
		Product product = null;
		boolean flag=true;
		int option;
		String serial,productName;
		double costPrice, sellingPrice,weight;
		int stock;
		do {
			System.out.println();
			System.out.println("1- To create an wholesalers product");
			System.out.println("2- To create a product in store");
			System.out.println("3- To create a product through website");
			option = (int) getValidNumber(sc,"Enter your choice\n",POSITIVE,Integer.class);
			System.out.println("Enter product serial");
			serial=sc.nextLine();//TODO: add sorted and check if serial already exist 
			System.out.println("Enter product name");
			productName=sc.nextLine();
			costPrice = (double)getValidNumber(sc,"Enter cost price\n",POSITIVE,Double.class);
			sellingPrice = (double)getValidNumber(sc,"Enter selling price\n",POSITIVE,Double.class);
			weight = (double)getValidNumber(sc,"Enter weight\n",POSITIVE,Double.class);
			stock = (int)getValidNumber(sc,"Enter stock\n",POSITIVE,Integer.class);
			switch(option) {
			case 1:
				product = ProductFactory.createProduct(eProduct.eProductWholesalers, serial, productName, costPrice, sellingPrice, stock, weight);
				flag=false;
				break;
			case 2:
				product = ProductFactory.createProduct(eProduct.eProductStore, serial, productName, costPrice, sellingPrice, stock, weight);
				flag=false;
				break;
			case 3:
				product = ProductFactory.createProduct(eProduct.eProductWebsite, serial, productName, costPrice, sellingPrice, stock, weight);
				flag=false;
				break;
			default:
				System.out.println("Invalid input");
				flag=true;
				break;
			}
		}while(flag);
		if(product!=null)
			systemFacade.addProduct(product);
	}
	
	
	public static eShipType getShipTypeMenu(Scanner sc) {
		eShipType type=eShipType.eNone;
		int tmp;
		boolean flag=true;
		do {
			System.out.println();
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
		return type;
	}

}
