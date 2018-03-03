/**
 * 
 */
package com.flowergarden.run;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;

import com.flowergarden.properties.FreshnessInteger;

import org.junit.Assert;

/**
 * @author SOIERR
 *
 */
public class SearchFlowersByLengthTest {
	
	private Bouquet<GeneralFlower> bouquet = null;
	private int roseLength = 100;
	
	@Before
	public void prepareBouquet(){
		
		Rose rose = new Rose(false,roseLength,50,new FreshnessInteger(80));
		Chamomile chamomile = new Chamomile(15, 70, 30, new FreshnessInteger(80));
		bouquet = new MarriedBouquet();
		bouquet.addFlower(rose);
		bouquet.addFlower(chamomile);
		
	}
	
	@Test
	public void searchFlowersByLenghtTest(){
		
		Collection<GeneralFlower> col = bouquet.searchFlowersByLenght(roseLength, roseLength);
		Iterator<GeneralFlower> it = col.iterator();
		GeneralFlower flower = it.next();
		Assert.assertEquals(flower.getClass().getName(), Rose.class.getName());
	}
	
}
