package databases;

import java.util.List;

import models.Stocks;
import models.iStocks;

public interface iStocksTable {
	public boolean create(iStocks stocks);
	//public void read();
	public List<Stocks> get();
	public Stocks get(String ticker);
	public boolean update(iStocks stocks);
	public boolean delete(iStocks stocks);
}
