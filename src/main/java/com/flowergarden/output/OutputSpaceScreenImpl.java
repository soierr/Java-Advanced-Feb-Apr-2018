/**
 * 
 */
package com.flowergarden.output;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.flowergarden.bouquet.Bouquet2;
import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author Sergey Slipchenko
 *
 */
public class OutputSpaceScreenImpl implements OutputSpaceInt{
	
	private String lineHello = "********************************************************************************************";
	private String lineTable = "------------------------------------------------------------------------------";
	
	@Override
	public void printHello() {
		
		System.out.print("\n");
		System.out.println(lineHello);
		System.out.print("\n");
		System.out.printf("%s%n", StringUtils.center(" Flower Garden Application", lineHello.length()));
		System.out.print("\n");
		System.out.printf("%s%n", StringUtils.center("Application is for flower bouquets managing and viewing", lineHello.length()));
		System.out.print("\n");
		System.out.println(lineHello);
		System.out.print("\n");
	}

	@Override
	public void printBouquets(List<Bouquet2<GeneralFlower2>> bouquets) {
		
		bouquets.sort(new Comparator<Bouquet2<GeneralFlower2>>() {

			@Override
			public int compare(Bouquet2<GeneralFlower2> o1, Bouquet2<GeneralFlower2> o2) {
				return o1.getId() - o2.getId();
			}
			
		});

		print(bouquets);
		
	}

	private void print(List<Bouquet2<GeneralFlower2>> bouquets){
		
		System.out.printf("\n");
		System.out.println(lineTable);
		System.out.printf("\n");
		System.out.printf("|%s|%s|%s|%s|%n",
				StringUtils.center("Id", 5),
				StringUtils.center("Name", 16),
				StringUtils.center("Price of assembling", 26),
				StringUtils.center("Total Price", 26));
		System.out.printf("\n");
		System.out.println(lineTable);
		
		for(Bouquet2<GeneralFlower2> bouquet : bouquets){
			
			System.out.printf("|%s|%s|%s|%s|%n",
					StringUtils.center(String.valueOf(bouquet.getId()), 5),
					StringUtils.center(String.valueOf(bouquet.getName()), 16),
					StringUtils.center(String.valueOf(bouquet.getPriceDetailed().getPriceAssembling()), 26),
					StringUtils.center(String.valueOf(bouquet.getPriceDetailed().getPriceTotal()), 26));
			
		}
		
		System.out.println(lineTable);
		System.out.printf("\n");
	}

}
