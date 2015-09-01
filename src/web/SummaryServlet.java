package web;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.*;
import databases.*;

public class SummaryServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		StockPricesTable prices = new StockPricesTable();
		UserTable userTable = new UserTable();
		StockOwnedTable stock = new StockOwnedTable();
		
		List<Summary> summary= new ArrayList<Summary>();
		List<StockPrices> currentPrices = prices.getByDate(ServletHelper.todaysDate());
		
		for (User user : userTable.get() )
		{
			summary.add(new Summary(user, stock.getOwnedStocksForSinglePlayer(user.getUserName() ), currentPrices) );
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(ServletHelper.createHead("Summary"));
		out.println("body");
		
			out.println("<h1>Summary</h1>");
			
			for (Summary s: summary  )
			{
				out.println("<h2>User: "+ s.getUser().getUserName()+ " - Net Worth: " + String.format("%.2f", s.getNetworth())+ "</h2>");
				out.println("<h4>Cash: "+ String.format("%.2f", s.getUser().getCash())+ "</h4>");
				
				out.println("<table border ='1'>");
				out.println("<tr>"
								+"<th> Ticker</th>"
								+"<th> Shares </th>" 
								+"<th> Current Price</th>"
								+"<th> Total</th>"
								+"</tr>");
				for ( StockOwned stockOwned : s.getOwnedStock() )
			    {
			    	String ticker = stockOwned.getTicker();
			    	int shares = stockOwned.getShares();
			    	double currentStockPrice = s.getPrice().get(ticker);
			    	
			    	out.println("<tr>");
			    	out.println("<td>" + ticker + "</td>" );
			    	out.println("<td>" + shares + "</td>" );
			    	out.println("<td>" + currentStockPrice + "</td>" );
			    	out.println("<td>" + String.format( "%.2f", currentStockPrice * shares ) + "</td>" );
			    	out.println("</tr>");
			    }
			    
				out.println("</table>");
		    }
		    
		    out.println("</body>");
	        out.println("</html>");
	    }
				
	}