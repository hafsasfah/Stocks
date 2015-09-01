package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
	public ConnectionHelper() {
		// TODO Auto-generated constructor stub
	}

	public static Connection connectionHelper() {
		Connection connection;
		try {
			String url = "jdbc:postgresql://localhost/StockGame";
			String username = "postgres";
			String password = "password";
			
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}