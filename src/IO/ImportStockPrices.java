package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.StockPrices;
import databases.StockPricesTable;

public class ImportStockPrices {
	public static void main(String []args){
		new ImportStockPrices(".\\S&P500-2015-06-05.csv","2015-06-05");
	}
	
	private StockPricesTable prices;
	private String filename;
	private String date;
	
	public ImportStockPrices(String filename, String date) {
		this.filename = filename;
		this.date = date;
		prices = new StockPricesTable();
		
		for (StockPrices stockPrices: read(filename)) {
			prices.create(stockPrices);
		}
	}

		public List<StockPrices>read (String filename1){
			File filename = new File(filename1);
			Scanner scanner;
			try {
				scanner = new Scanner(filename);
				List<StockPrices> stockPrices = new ArrayList<StockPrices>();
				
				if (scanner.hasNext()) {
					String headerLineToIgnore = scanner.nextLine();
				}
				
				while (scanner.hasNext()) {
					String line = scanner.nextLine();
					int firstCommaIndex = line.indexOf(',');
					String ticker = line.substring(0, firstCommaIndex );
					double price = Double.parseDouble(line.substring(firstCommaIndex + 1));
					stockPrices.add(new StockPrices(ticker, date, price));
				}
				
				scanner.close();
				return stockPrices;
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		return null;
		}		
}