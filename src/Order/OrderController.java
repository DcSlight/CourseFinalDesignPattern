package Order;

import java.util.Stack;

import Exception.StockException;
import Interfaces.IUndoCommand;
import Products.Product;

public class OrderController {
	private Stack<IUndoCommand> stack;
	
	public OrderController() {
		stack = new Stack<>();
	}
	
	public void updateOrders(Order order, Product product) throws StockException {
		IUndoCommand cmd = new OrderUpdateCommand(product,order);
		cmd.execute();
		stack.add(cmd);
	}

	public void undoOrder() {
		if (!stack.isEmpty()) {
			IUndoCommand cmd = stack.pop();
			cmd.undo();
		}
	}
}
