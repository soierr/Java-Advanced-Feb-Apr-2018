package com.flowergarden.run;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.dao.DaoBouquet;
import com.flowergarden.flowers.GeneralFlower2;
import com.flowergarden.output.OutputSpaceInt;
import com.flowergarden.spring.ctx.Beans;

@SuppressWarnings("unchecked")
public class Run {
	
	public static void main(String[] args){
		
		/*changed context container type from xml to annotation*/
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		
		DaoBouquet daoBouquet = ctx.getBean("daoBouquet", DaoBouquet.class);
		
		OutputSpaceInt output = ctx.getBean("output", OutputSpaceInt.class);
				
		List<Bouquet2<GeneralFlower2>> bouquets = (List<Bouquet2<GeneralFlower2>>) daoBouquet.getBouquets();
		
		output.printHello();
		output.printBouquets(bouquets);
		
	}

}
