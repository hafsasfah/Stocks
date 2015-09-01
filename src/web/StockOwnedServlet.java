package web;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.*;
import databases.*;

public class StockOwnedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		StockOwnedTable stockOwnedTable = new StockOwnedTable();
            	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println( ServletHelper.createHead( "Stock Owned" ) );
        out.println("<body>");
        
        String pathInfo = request.getPathInfo();
        
        if ( pathInfo == null || pathInfo.equals("/") )
        {
	        out.println("<h1>Stock Owned</h1>");
	        out.println("<table border='1'>");
	        List<StockOwned> ownedStocks = stockOwnedTable.get();
	        out.println( "<tr><th>User</th><th>Ticker</th><th>Shares</th></tr>" );
	        
	        for ( StockOwned ownedStock : ownedStocks ) {
	        	out.println( "<tr>" );
	        	out.println( "<td>" + ownedStock.getUserName() + "</td>" );
	        	out.println( "<td>" + ownedStock.getTicker() + "</td>" );
	        	out.println( "<td>" + ownedStock.getShares() + "</td>" );
	        	out.println( "</tr>" );
	        }
	        
	        out.println("</table>");
        }
        else
        {
        	/*String requestedPlayer = pathInfo.substring( 1 );
        	
        	Player player = playerRepository.getSinglePlayer(requestedPlayer) ;
        	if ( player == null )
        	{
        		out.println( requestedPlayer + " was not found" );
        	}
        	else
        	{
        		out.println( player.getName() + " " + player.getMoney() );
        	}*/
        }
	    out.println("</body>");
        out.println("</html>");
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws IOException, ServletException
    {
		StockOwnedTable stockOwnedTable = new StockOwnedTable();
    	
    	response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        
        Map<String, String[]> parameterMap = request.getParameterMap();
        
        if ( parameterMap.containsKey("Ticker") && parameterMap.containsKey("UserName")  && parameterMap.containsKey("Shares") )
        {
        	UserTable userTable = new UserTable();
        	User user = userTable.getSingleUser( parameterMap.get("UserName")[0] );
        	
        	StockPricesTable stockPricesTable = new StockPricesTable();
        	
        	List<StockPrices> openingPrice = stockPricesTable.getByTickerAndDate(parameterMap.get("Ticker")[0], ServletHelper.todaysDate() ); 	
        	
        	if (openingPrice.size() > 0) {
        		double todaysOpeningPrice = openingPrice.get(0).getPrice();
        		double costOfStock = todaysOpeningPrice * Integer.parseInt(parameterMap.get("Shares")[0]);
        		
        		if ( user.getCash() > costOfStock ) {
	        		if ( stockOwnedTable.create(new StockOwned(parameterMap.get("UserName")[0], parameterMap.get("Ticker")[0],
	            			Integer.parseInt(parameterMap.get("Shares")[0]))))
	            	{
	            		out.println("Created StockOwned!");
	            		user.setCash( user.getCash() - costOfStock );
	            		userTable.update(user);
	            	}
	            	else
	            	{
	            		out.println("Something bad happened!!!");
	            	}
        		}
        		else
        		{
        			out.println("User does not have enough money to buy " + parameterMap.get("Ticker")[0]);
        		}
        	}
        	else
        	{
        		out.println("No price data found for stock: " + parameterMap.get("Ticker")[0]);
        	}
        }
        else
        {
        	out.println("You have to have a Ticker and UserName and Shares parameter!");
        }
    }
	
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
		StockOwnedTable stockOwnedTable = new StockOwnedTable();
    	
    	response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        String userName;
        String ticker;
        int shares;
        
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        	String data = br.readLine();
        	
        	String[] keyValuePairs = data.split("&");
        	
        	Map<String,String> putData = new HashMap<String,String>();
        	
        	for (String keyValuePair : keyValuePairs) {
        		String[] splitLine = keyValuePair.split("=");
        		putData.put(splitLine[0], splitLine[1]);
        	}
        	
        	if (putData.containsKey("UserName") && putData.containsKey("Ticker") && putData.containsKey("Shares")) {
        		stockOwnedTable.update(new StockOwned( putData.get("UserName"), 
        				putData.get("Ticker"), Integer.parseInt( putData.get("Shares"))));
        	}
        	
        	else {
        		out.println( "You must provide a UserName, Ticker and Shares!");
        	}
        		
        	
        }
        catch ( Exception e )
        {
        	out.println( "Couldn't real input stream request params" );
        }
        
    }
	
}
