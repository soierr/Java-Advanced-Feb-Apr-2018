package com.flowergarden.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.bouquet.Price;
import com.flowergarden.dao.DaoBouquet;
import com.flowergarden.dao.DaoDataSource;
import com.flowergarden.dao.impl.DaoBouquetImpl;
import com.flowergarden.dao.impl.DaoDataSourceSqlLiteImpl;
import com.flowergarden.dao.model.BouquetImpl;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.output.OutputSpaceInt;
import com.flowergarden.output.OutputSpaceScreenImpl;

@SuppressWarnings("unchecked")
public class Run {
	
	private static final String SQL_SCRIPTS_PATH = "resources/sql-scripts.txt";
	private static final String SAMPLE_BOUQUET_NAME = "First Date";
	
	public static void main(String[] args){
		
		DaoDataSource dataSource = new DaoDataSourceSqlLiteImpl();
		
		Properties sql = new Properties();
		
		try{
			
			sql.load(new FileInputStream(new File(SQL_SCRIPTS_PATH)));
			
		}catch(IOException e){
			
			e.printStackTrace();
		}

		DaoBouquet daoBouquet = new DaoBouquetImpl(dataSource, sql);
		
		OutputSpaceInt output = new OutputSpaceScreenImpl();
				
		List<BouquetImpl> bouquets = (List<BouquetImpl>) daoBouquet.getBouquets();
		
		output.printHello();
		output.printBouquets(bouquets);
		
		/*Just to demonstrated lesson3 home work method*/
		Bouquet2<GeneralFlower2> bouquet = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet(SAMPLE_BOUQUET_NAME);
		Price price = bouquet.getPriceDetailed();
		
	}

}
