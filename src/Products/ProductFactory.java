package Products;

import eNums.eProduct;

public class ProductFactory {
	public static Product createAdapterInvoice(eProduct type,String serial,String productName,double costPrice,double sellingPrice,int stock) {
		switch(type) {
		case eProductWebsite:
			return new ProductSoldThroughWebsite(serial, productName, costPrice, sellingPrice, stock);
		case eProductStore:
			return new ProductSoldInStore(serial, productName, costPrice, sellingPrice, stock);
		case eProductWholesalers: 
			return new ProductSoldToWholesalers(serial, productName, costPrice, sellingPrice, stock);
		default:
			throw new IllegalArgumentException();
		}
	}
}
