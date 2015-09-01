package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ImportTickerNameandStockName {
	ArrayList<String> dataArray;
	
		public static void main(String args[]){
				
				
			}
			
			public void ImportStockNames(){
			String filename1 = ( "s_p500stocks.csv" );
			File file = new File( filename1 );
			Scanner infile;
			try {
				infile = new Scanner( file );
				while( infile.hasNextLine() ){
					dataArray.add( infile.nextLine() );
				}
				}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
			
			public void ImportTodayPrice(){
				String filename = "S&P500-2015-06-01.csv";
				ImportStockNames();
				DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/DD");
				Date date = new Date();
				Connection connection;  
		        String url = "jdbc:postgresql://localhost/StockGame";
		        String user = "postgres";
		        String password = "password";
		        try 
		        {
		        	connection = DriverManager.getConnection(url, user, password);
		        
			        Statement db = connection.createStatement();
			        db.executeUpdate("DELETE FROM \"opening price\"");
			      
			       for(int i = 1;i<dataArray.size()-1;i++){ 
			    	
			    	   String str =  dataArray.get(i);
			    	   if(str.contains("'")){
			    		  String temp[]= str.split("'");
			    		  str = temp[0]+temp[1];
			    		   
			    	   }
			    	   String[] tokens = str.split(",");
			    	   if(tokens.length> 2){
			    		   tokens[1] = tokens[1]+","+tokens[2];
			    	   }
			    	   System.out.println(tokens[0]+" : "+tokens[1]);
			       db.executeUpdate("INSERT INTO \"opening price\" (\"ticker\", \"price\",\"date\") VALUES('"+tokens[0]+"','"+tokens[1]+"','"+dateFormat.format(date)+"')");
			       }
		        }
		        catch(SQLException e)
		        {
		        	e.printStackTrace();
		        	System.err.println("Got an exception! "); 
		        }
	}
		}
		
