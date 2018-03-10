/**
 * 
 */
package com.flowergarden.properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;


/**
 * @author SOIERR
 *
 */
public class SortByFreshnessTest {
		
	Bouquet<GeneralFlower> bouquet = new MarriedBouquet();
	
	Rose rose = null;
	Rose rose2 = null;
	
	@Before
	public void prepareBouquet(){
		
		boolean spike = true;
		int length = 85;
		float price = 100;
		
		rose = new Rose(spike, length, price, new FreshnessInteger(97));
		rose2 = new Rose(spike, length, price, new FreshnessInteger(96));
		
		bouquet.addFlower(rose);
		bouquet.addFlower(rose2);		
		
	}
	
	@Test
	public void sortByFreshnessAscTest(){
		
		bouquet.sortByFreshness();
		
		/*Check object identity*/
		Assert.assertTrue(rose2 == bouquet.getFlowers().iterator().next());
	}
}
