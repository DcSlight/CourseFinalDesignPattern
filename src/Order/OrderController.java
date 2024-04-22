package Order;

import java.util.Stack;

import Exception.StockException;
import Interfaces.IUndoCommand;
import Products.Product;

public class OrderController implements Cloneable{
	private Stack<IUndoCommand> stack;
	
	public OrderController() {
		stack = new Stack<>();
	}
	
	@Override
    public OrderController clone() throws CloneNotSupportedException {
        OrderController clonedController = (OrderController) super.clone();
        clonedController.stack = new Stack<>();
        clonedController.stack.addAll(stack);
        // Deep copy of the stack (create new command instances)
//        for (IUndoCommand command : this.stack) {
//            // Assuming IUndoCommand has a clone method (implemented by its concrete classes)
//            clonedController.stack.push(((OrderUpdateCommand)command).clone());
//        }

        return clonedController;
    }
	
	public boolean haveOrders() {
		if (stack.isEmpty())
			return false;
		return true;
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
