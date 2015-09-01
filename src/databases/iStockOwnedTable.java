package databases;

import models.StockOwned;
import models.iStockOwned;

public interface iStockOwnedTable {
	public boolean create(StockOwned stocksOwned);
	//public void read();
	public boolean update(StockOwned stocksOwned);
	public boolean delete(StockOwned stocksOwned);
}
