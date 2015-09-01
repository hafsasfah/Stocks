package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.StockOwned;
import models.iStockOwned;

/*
 * PK (UserID, Ticker), Shares
 * 
 * UserID = int
 * Ticker = String
 * Shares = int
 */

public class StockOwnedTable implements iStockOwnedTable {
	Connection connection;

	public StockOwnedTable() {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/StockGame";
			String username = "postgres";
			String password = "password";
			
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(StockOwned stocksOwned) {
		try {
			Statement statement = connection.createStatement();
			String createStocksOwnedRow = String.format
					("INSERT INTO \"StocksOwned\" (\"UserID\", \"Ticker\", \"Shares\")"
							+ "VALUES ('%s', '%s', '%s');", 
							stocksOwned.getUserID(), stocksOwned.getTicker(), stocksOwned.getShares());
			statement.execute(createStocksOwnedRow);
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(StockOwned stocksOwned) {
		return false;
	}

	public List<StockOwned> get()
	{
		List<StockOwned> ownedStocks = new ArrayList<StockOwned>();
		try { 		
			Statement statement = connection.createStatement();
			String query = String.format("SELECT \"UserID\", \"Ticker\", \"Shares\" FROM \"StocksOwned\"");
			ResultSet results = statement.executeQuery(query);
			while (results.next()) {
				ownedStocks.add(new StockOwned(results.getString(1), results.getString(2), results.getInt(3)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ownedStocks;
	}
	
	public List<StockOwned> getOwnedStocksForSinglePlayer(String userID)
	{
		List<StockOwned> ownedStocks = new ArrayList<StockOwned>();
		try {
			Statement statement = connection.createStatement();
			String query = String.format("SELECT \"UserID\", \"Ticker\", \"Shares\" FROM \"StocksOwned\" "
					+ "WHERE \"UserID\" = '%s'", userID);
			ResultSet results = statement.executeQuery(query);
			while ( results.next() )
			{
				ownedStocks.add(new StockOwned(results.getString(1), results.getString(2), results.getInt(3)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ownedStocks;
	}
	
	public boolean update(StockOwned stockOwned)
	{
		try {
			Statement statement = connection.createStatement();
			String insertSQL = String.format("UPDATE \"StocksOwned\" SET \"Shares\" = %d "
					+ "WHERE \"UserID\" = '%s' and \"Ticker\" = '%s'", 
					stockOwned.getShares(), stockOwned.getUserID(), stockOwned.getTicker() );
			statement.executeUpdate(insertSQL);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
