package repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class StockConnection{
	public static Connection createconnection()
	{
		
	
{
	String url = "jdbc:postgresql://localhost/stock market";
    String user = "postgres";
    String password = "password";
    
    try{
    	Class.forName("org.postgresql.Driver");
    	return DriverManager.getConnection( url, user, password );
    }
    catch(Exception e)
    {
    e.printStackTrace();
    }
    return null;
}
	}
}
