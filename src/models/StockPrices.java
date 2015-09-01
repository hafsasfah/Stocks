package models;

public class StockPrices {
	
	private String date;
	private double price;
	private String ticker;
	
	public StockPrices() {}
	
	public StockPrices(String ticker, String date, double price)
	{
		this.setTicker(ticker);
		this.setDate(date);
		this.setPrice(price);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
}