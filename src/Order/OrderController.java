package Order;

import java.util.Stack;

import Exception.StockException;
import Interfaces.IUndoCommand;
import Products.Product;
import Products.Product.ProductMemento;

public class OrderController implements Cloneable{
	private Stack<IUndoCommand> stack;
	private OrderControllerMemento memento;
	
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
	
	public void createMemento() {
		memento= new OrderControllerMemento(stack);
	}

	public void setMemento() {
		this.stack = memento.stack;
	}
	
	public static class OrderControllerMemento{
		private Stack<IUndoCommand> stack;
		
		private OrderControllerMemento(Stack<IUndoCommand> stack){
			this.stack =new Stack<>();
			Stack<IUndoCommand> tmp = new Stack<>();
			while(!stack.isEmpty()) {
				tmp.push(stack.pop());
			}
			
			while(!tmp.isEmpty()) {
				stack.push(tmp.lastElement());
				this.stack.push(tmp.pop());
			}
		}
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
