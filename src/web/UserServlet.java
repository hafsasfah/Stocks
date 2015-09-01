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

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
    {
        UserTable userTable = new UserTable( );
            	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println( ServletHelper.createHead( "User" ) );
        out.println("<body>");
        
        String pathInfo = request.getPathInfo();
        
        if ( pathInfo == null || pathInfo.equals("/") )
        {
	        out.println("<h1>Users</h1>");
	        out.println("<table border='1'>");
	        List<User> users = userTable.get();
	        if ( users.size() > 0 )
	        {
	        	out.println( "<tr><th>UserName</th><th>Cash</th></tr>" );
	        }
	        for ( User user : users )
	        {
	        	out.println( "<tr>" );
	        	out.println( "<td>" + user.getUserName() + "</td>" );
	        	out.println( "<td>" + String.format( "%.2f", user.getCash()) + "</td>" );
	        	out.println( "</tr>" );
	        }
	        
	        out.println("</table>");
        }
        else
        {
        	String requestedUser = pathInfo.substring(1);
        	
        	User user = userTable.getSingleUser(requestedUser) ;
        	if ( user == null )
        	{
        		out.println( requestedUser + " was not found" );
        	}
        	else
        	{
        		out.println(user.getUserName() + " " + user.getCash());
        	}
        }
	    out.println("</body>");
        out.println("</html>");
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws IOException, ServletException
    {
		UserTable userTable = new UserTable();
    	
    	response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        
        Map<String, String[]> parameterMap = request.getParameterMap();
        
        if ( parameterMap.containsKey("UserName") )
        {
        	User user = new User( parameterMap.get("UserName")[0], new Random().nextInt() );
        	if (userTable.create(user))
        	{
        		out.println("User Created! Money is a bitch that never sleeps!");
        	}
        	else
        	{
        		out.println("Something bad happened!!! Bulls make money. Bears make money. Pigs? They get slaughtered.");
        	}
        }
        else
        {
        	out.println("You have to have a name parameter! The mother of all evil is speculation.");
        }
    }
	
	
}