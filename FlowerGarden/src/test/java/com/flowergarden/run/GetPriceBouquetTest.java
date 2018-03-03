/**
 * 
 */
package com.flowergarden.run;

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
public class GetPriceBouquetTest {

	private Bouquet<GeneralFlower> bouquet = null;
	private float chamomilePrice = 50f;
	private float rosePrice = 75f;
	private float assembledPrice = 100;
	
	@Before
	public void setFlowerPrice(){

		bouquet = new MarriedBouquet();
		bouquet.addFlower(new Rose(true, 85, rosePrice, new FreshnessInteger(89)));
		bouquet.addFlower(new Chamomile(15, 75, chamomilePrice, new FreshnessInteger(75)));
		bouquet.addFlower(new Chamomile(15, 75, chamomilePrice, new FreshnessInteger(75)));
		((MarriedBouquet)bouquet).setAssembledPrice(assembledPrice);
		
	}
	
	@Test
	public void getFlowerPriceTest(){
		
		Assert.assertEquals((2*chamomilePrice)+rosePrice, bouquet.getPrice(), assembledPrice);
	}

}
