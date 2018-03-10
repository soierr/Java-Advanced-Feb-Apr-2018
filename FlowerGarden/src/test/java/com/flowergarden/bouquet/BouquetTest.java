/**
 * 
 */
package com.flowergarden.bouquet;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

/**
 * @author SOIERR
 *
 */
public class BouquetTest {

	private Bouquet<GeneralFlower> bouquet = null;
	
	@Before
	public void createBouquet(){

		bouquet = new MarriedBouquet();
	}
	
	@Test
	public void getPriceBouquetTest(){
		
		float chamomilePrice = 50f;
		float rosePrice = 75f;
		float assembledPrice = 100;
		
		bouquet.addFlower(new Rose(true, 85, rosePrice, new FreshnessInteger(89)));
		bouquet.addFlower(new Chamomile(15, 75, chamomilePrice, new FreshnessInteger(75)));
		bouquet.addFlower(new Chamomile(15, 75, chamomilePrice, new FreshnessInteger(75)));
		((MarriedBouquet)bouquet).setAssembledPrice(assembledPrice);
		
		Assert.assertEquals((2*chamomilePrice)+rosePrice, bouquet.getPrice(), assembledPrice);
	}
	
	@Test
	public void searchFlowersByLenghtBouquetTest(){
		
		int roseLength = 100;
		int camomileLength = 70;
		
		Rose rose = new Rose(false,roseLength,50,new FreshnessInteger(80));
		Chamomile chamomile = new Chamomile(15, camomileLength, 30, new FreshnessInteger(80));
		bouquet = new MarriedBouquet();
		bouquet.addFlower(rose);
		bouquet.addFlower(chamomile);
		
		Collection<GeneralFlower> col = bouquet.searchFlowersByLenght(roseLength, roseLength);
		Iterator<GeneralFlower> it = col.iterator();
		GeneralFlower flower = it.next();
		Assert.assertEquals(flower.getClass().getName(), Rose.class.getName());
	}
	
	@Test(expected=NullPointerException.class)
	public void sortByFreshnessNPEBouquetTest(){
		
		bouquet.addFlower(new GeneralFlower());
		bouquet.addFlower(new GeneralFlower());
		
		bouquet.sortByFreshness();
	}
	
	@Test
	public void sortByFreshnessAscBouquetTest(){
		
		boolean spike = true;
		int length = 85;
		float price = 100;
		
		Rose rose = new Rose(spike, length, price, new FreshnessInteger(97));
		Rose rose2 = new Rose(spike, length, price, new FreshnessInteger(96));
		
		bouquet.addFlower(rose);
		bouquet.addFlower(rose2);	
		
		bouquet.sortByFreshness();
		
		/*Check object identity*/
		Assert.assertTrue(rose2 == bouquet.getFlowers().iterator().next());
	}

}
