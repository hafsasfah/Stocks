package web;

import IO.ImportStockReader;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import models.*;
import databases.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import models.*;
import databases.*;

public class StocksServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    	StocksTable stocksTable = new StocksTable();
            	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println( ServletHelper.createHead( "Stock" ) );
        out.println("<body>");
        
        String pathInfo = request.getPathInfo();
        
        if ( pathInfo == null || pathInfo.equals("/") )
        {
	        out.println("<h1>S&P 500 Stocks</h1>");
	        out.println("<table border='1'>");
	        List<Stocks> stocks = stocksTable.get();
	        if ( stocks.size() > 0 )
	        {
	        	out.println( "<tr><th>Ticker</th><th>Name</th></tr>" );
	        }
	        for ( Stocks stock : stocks )
	        {
	        	out.println( "<tr>" );
	        	out.println( "<td>" + stock.getTicker() + "</td>" );
	        	out.println( "<td>" + stock.getStockName() + "</td>" );
	        	out.println( "</tr>" );
	        }
	        
	        out.println("</table>");
        }
        else
        {
        	String requestedTicker = pathInfo.substring(1);
        	
        	Stocks stock = stocksTable.get(requestedTicker) ;
        	if ( stock == null )
        	{
        		out.println( requestedTicker + " was not found" );
        	}
        	else
        	{
        		out.println( stock.getTicker() + " " + stock.getStockName() );
        	}
        }
	    out.println("</body>");
        out.println("</html>");
    }
}