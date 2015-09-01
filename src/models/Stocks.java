package models;

public class Stocks {

	private String ticker;
	private String stockName;	
	
	public Stocks(String ticker, String stockName)
	{
		this.ticker = ticker;
		this.stockName = stockName;
	}
	
	public Stocks()
	{
		
	}
	
	public String getTicker()
	{	
		return ticker;
	}
	
	public void setTicker(String ticker)
	{
		this.ticker = ticker;
	}
	
	public String getStockName()
	{
		return stockName;	
	}
	
	public void setStockName(String stockName)
	{
		this.stockName = stockName;
	}
	
}
