package Interfaces;

import Exception.StockException;

public interface IUndoCommand {
	void execute() throws StockException;
	void undo();
}
