package Shipping;

import java.util.HashSet;
import java.util.Set;

import Interfaces.IShippingExpressCommand;
import Interfaces.IShippingStandardCommand;
import Products.Product;
import eNums.eShipType;

public class ShippingController {
	private Set<IShippingExpressCommand> express; 
	private Set<IShippingStandardCommand> standard;
	private Set<ShippingCompany> companies;
	
	public ShippingController() {
		this.express = new HashSet<>();
		this.standard = new HashSet<>();
		this.companies =  new HashSet<>();
	}
	
	public double getMinShippment(eShipType type,Product p) {
		double min=-1;
		//setProduct
		switch(type) {
		case Express:
			min=getMinExpress();
			break;
		case Standard:
			break;
		}
		return min;
	}
	
	public double getMinExpress() {
		double min = Double.MAX_VALUE;
		double tmp;
		//String company="";
		for(IShippingExpressCommand i : express) {
			tmp = i.calcExpress();
			if(min > tmp){
				min = tmp;
			//	company = i.toString();
			}
		}
		return min;
	}
	
//	public Set<IShippingExpressCommand> getExpress() {
//		return express;
//	}
//
//	public Set<IShippingStandardCommand> getStandard() {
//		return standard;
//	}
	
	public void setProduct() {
	
	}
	
	public void addExpress(IShippingExpressCommand e) {
		express.add(e);
	}
	
	public void addExpress(IShippingStandardCommand s) {
		standard.add(s);
	}
	

	
	
}
