package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ImportTickerSymbolAndPrice {
	 
	
	
	public static void ImportTickerSymbolAndPrice () throws FileNotFoundException {
	        Scanner scanner = new Scanner(new File("s_p500-2015-06-01.csv"));
	        scanner.useDelimiter(",");
	        while(scanner.hasNext()){
	            System.out.print(scanner.next()+"|");
	        }
	        scanner.close();
	    }
	
	public static void main (String args[]) throws FileNotFoundException{
		ImportTickerSymbolAndPrice();
	}

}
