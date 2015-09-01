package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.StockPrices;
import databases.*;

public class OpeningPriceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		StockPricesTable stockPricesTable = new StockPricesTable();
    	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(ServletHelper.createHead("OpeningPrice"));
        out.println("<body>");
        
        Map<String, String[]> parameterMap = request.getParameterMap();

        List<StockPrices> prices = new ArrayList<StockPrices>();
        String ticker = "ticker";
        String date = "date";
        
        boolean requestByTicker = parameterMap.containsKey(ticker);
        boolean requestByDate = parameterMap.containsKey(date);
        
        if (requestByTicker && requestByDate) {
        	prices.addAll(stockPricesTable.getByTickerAndDate(parameterMap.get(ticker)[0], parameterMap.get(date)[0]));
        }
        else if (requestByTicker) {
        	prices.addAll(stockPricesTable.getByTicker(parameterMap.get(ticker)[0]));
        	
        }
        else if (requestByDate) {
        	prices.addAll(stockPricesTable.getByDate(parameterMap.get(date)[0]));
        }
        else {
        	prices.addAll(stockPricesTable.get());
        }
        
        out.println (buildStockPricesTable(prices));
        /*
        out.println("<table border='1'>");
        out.println("<tr><th>Ticker</th><th>Price</th><th>Date</th></tr>");
        
        for (StockPrices price : prices) {
        	out.println("<tr>");
        	out.println("<td>" + price.getTicker() + "</td>");
        	out.println("<td>" + price.getPrice() + "</td>");
        	out.println("<td>" + price.getDate() + "</td>");
        	out.println("</tr>");
        }
        out.println("</table>");
        */
        
        out.println("</body>");
        out.println("</html>");
    }

	private String buildStockPricesTable( List<StockPrices> prices )
	{
		StringBuilder output = new StringBuilder();
		
		output.append("<table border='1'>");
		output.append("<tr><th>Ticker</th><th>Price</th><th>Date</th></tr>");
        
        for (StockPrices price : prices) {
        	output.append("<tr>");
        	output.append("<td>" + price.getTicker() + "</td>");
        	output.append("<td>" + price.getPrice() + "</td>");
        	output.append("<td>" + price.getDate() + "</td>");
        	output.append("</tr>");
        }
        output.append("</table>");
        
        return output.toString();
	}
	
}