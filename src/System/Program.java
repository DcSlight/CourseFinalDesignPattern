package System;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import Components.Contact;
import Components.Customer;
import Exception.StockException;
import Order.Order;
import Products.Product;
import Products.ProductFactory;
import Products.ProductSoldThroughWebsite;
import Shipping.*;
import eNums.eProduct;
import eNums.eShipType;

public class Program {
	
	
	public static final String ADD_PRODUCT="1";
	public static final String EXIT_1="E";
	public static final String EXIT_2="e";
	public static final Boolean POSITIVE=true;

	public static void main(String[] args) throws Exception  {
		Scanner sc = new Scanner(System.in);
		SystemFacade systemFacade = SystemFacade.getInstance();
		boolean flag = true;
		String option;
		System.out.println("Welcome to the System!");
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
				addProductMenu(sc);
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
	
	
	public static <T extends Number> Number getValidNumber(Scanner scanner, String prompt,boolean needToBePositive,Class<T> type) {
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
                	if(tmp.doubleValue() > 0)
                		return tmp;
                	throw  new InputMismatchException();
                }
                return tmp;	
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine();
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
	
	public static void addProductMenu(Scanner sc) {
		Product p;
		int option;
		String serial,productName;
		double costPrice, sellingPrice,weight;
		int stock;
		do {
			System.out.println("1- To create an wholesalers product");
			System.out.println("2- To create a product in store");
			System.out.println("3- To create a product through website");
			option = (int) getValidNumber(sc,"Enter your choice\n",POSITIVE,Integer.class);
			switch(option) {
			case 1:
				//p=ProductFactory.createProduct(eProduct.eProductWholesalers, EXIT_1, ADD_PRODUCT, option, option, option, option)
				System.out.println("enter");
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
		}while(true);
	}
	
	
	public static eShipType getShipTypeMenu(Scanner sc) {
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
		return type;
	}

}
