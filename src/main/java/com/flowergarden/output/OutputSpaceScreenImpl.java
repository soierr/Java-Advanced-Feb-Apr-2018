/**
 * 
 */
package com.flowergarden.output;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.flowergarden.dao.model.BouquetImpl;

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
	public void printBouquets(List<BouquetImpl> bouquets) {
		
		bouquets.sort(new Comparator<BouquetImpl>() {

			@Override
			public int compare(BouquetImpl o1, BouquetImpl o2) {
				return o1.getId() - o2.getId();
			}
			
		});

		print(bouquets);
		
	}

	private void print(List<BouquetImpl> bouquets){
		
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
		
		for(BouquetImpl bouquet : bouquets){
			
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
