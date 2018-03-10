package com.flowergarden.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.dao.DaoBouquet;
import com.flowergarden.dao.DaoDataSource;
import com.flowergarden.dao.impl.DaoBouquetImpl;
import com.flowergarden.dao.impl.DaoDataSourceSqlLiteImpl;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.GeneralFlower2;

@SuppressWarnings("unchecked")
public class Run {

	/*Run the CreateDbAndSampleData before to create db and fill the data*/	
	public static void main(String[] args){
		
		DaoDataSource dataSource = new DaoDataSourceSqlLiteImpl();
		
		Properties sql = new Properties();
		
		try{
			
			sql.load(new FileInputStream(new File("resources/sql-dml.txt")));
			
		}catch(IOException e){
			
			e.printStackTrace();
		}

		DaoBouquet daoBouquet = new DaoBouquetImpl(dataSource, sql);
		
		daoBouquet.deleteBouquet(0);
		
		Bouquet<GeneralFlower> bouquet = (Bouquet<GeneralFlower>) daoBouquet.getBouquet("First Date");
		
		Bouquet2<GeneralFlower2> bouquet2 = (Bouquet2<GeneralFlower2>) daoBouquet.getBouquet("First Date");
		
		

	}

}
