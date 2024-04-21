package Order;

import Exception.StockException;
import Interfaces.IUndoCommand;
import Products.Product;

public class OrderUpdateCommand implements IUndoCommand{
	private Product product;
	//private Product previousProduct;
	private Order order;
	private Order previousOrder;
	private int stock;
	private int previousStock;
	
	public OrderUpdateCommand(Product product,Order order) {
		this.product=product;
		this.order=order;
		this.stock = product.getStock();
	}

	@Override
	public void execute() throws StockException {
		previousOrder = order;
		previousStock = stock;
		product.addOrder(order);
	}

	@Override
	public void undo() {
		product.removeOrder(order);
		product.setStock(previousStock);
	}
}
