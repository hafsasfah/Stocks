package IO;
import models.Stocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import repository.StockConnection;
import databases.*;

public class ImportStockReader {
	public static void main(String []args){
		new ImportStockReader(".\\S&P500Stocks.csv");
	}	
	
	private StocksTable stocksTable;
	
	public ImportStockReader(String filename){
		
		stocksTable=new StocksTable();
		for (Stocks stock: read(filename))
		{
			stocksTable.create(stock);
		}
	}

		public List<Stocks>read ( String filename1 ){
			File filename = new File( filename1 );
			Scanner scanner;
			try 
			{
				scanner = new Scanner(filename);
				List<Stocks> stocks = new ArrayList<Stocks>();
				if ( scanner.hasNext() )
				{
					String headerLineToIgnore = scanner.nextLine();
				}
				while ( scanner.hasNext() )
				{
					String line = scanner.nextLine();
					int firstCommaIndex = line.indexOf(',');
					String ticker = line.substring(0, firstCommaIndex );
					String name = line.substring(firstCommaIndex + 1).replace("\"", "").replace("'","");
					stocks.add( new Stocks(ticker, name) );
				}
				scanner.close();
				return stocks;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null;
		}
		
}
