package com.flowergarden.run;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.dao.DaoBouquet;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.output.OutputSpaceInt;

@SuppressWarnings("unchecked")
public class Run {

	private static final String APPLICATION_CONTEXT = "application-context.xml";
	
	public static void main(String[] args){
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
		
		/*this what exactly we have changed, avoiding new operator and explicit properties loading from file*/
		//DaoBouquet daoBouquet = new DaoBouquetImpl(dataSource, sql);
		
		DaoBouquet daoBouquet = ctx.getBean("daoBouquet", DaoBouquet.class);
		
		OutputSpaceInt output = ctx.getBean("output", OutputSpaceInt.class);
				
		List<Bouquet2<GeneralFlower2>> bouquets = (List<Bouquet2<GeneralFlower2>>) daoBouquet.getBouquets();
		
		output.printHello();
		output.printBouquets(bouquets);
		
	}

}
