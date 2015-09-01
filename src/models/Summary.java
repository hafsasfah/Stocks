package models;
import java.util.*;

public class Summary {
	
	private User user;
	private List<StockOwned> stock;
	private List<StockPrices> currentStock;
	private TreeMap<String,Double> priceTree;
	
	public Summary(User user, List<StockOwned> stock, List<StockPrices> currentStock)
	{
		this.user=user;
		this.stock=stock;
		this.currentStock=currentStock;
		priceTree=new TreeMap<String,Double>();
		
		for (StockPrices price : currentStock)
		{
			priceTree.put(price.getTicker(),price.getPrice());
		}
	}
	
	public User getUser()
	{
		return user;
	}
	
	public List<StockOwned> getOwnedStock()
	{
		return stock;
	}
	
	public List<StockPrices> getCurrentStock()
	{
		return currentStock;
	}
	
	public double getNetworth()
	{
		double netWorth=user.getCash();
		
		for (StockOwned stock: stock)
		{
			netWorth+= priceTree.get(stock.getTicker());
		}
		return netWorth;
	}
	public TreeMap<String,Double> getPrice()
	{
		return priceTree;
	}
	

}
