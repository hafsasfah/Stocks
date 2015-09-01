package models;

import java.util.Random;

public class StockOwned {
	private String userName;
	private String ticker;
	private int shares;
	private int userID;
	

	public StockOwned(String userName, String ticker, int shares) {
		this.setUserName(userName);
		this.setTicker(ticker);
		this.setShares(shares);
		this.setUserID(new Random().nextInt());
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}
		

}
