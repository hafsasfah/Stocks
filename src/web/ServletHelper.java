package web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ServletHelper {
	public static String createHead( String title )
	{
		StringBuilder output = new StringBuilder();
		output.append("<html>");
		output.append("<head>");
		output.append("<title>" + title + "</title>");
		output.append("</head>");
		
		return output.toString();
	}
	
	public static String todaysDate()
	{
		Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
	}
}
