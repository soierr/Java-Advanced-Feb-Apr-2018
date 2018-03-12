/**
 * 
 */
package com.flowergarden.flowers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

/**
 * @author SOIERR
 *
 */
public class FlowerTest {
	
	private final float etalonPrice = 50f;
	private final float deltaZero = 0f;
	private Flower<Integer> rose = null;
	
	
	
	@Before
	public void createFlower(){
		
		int length = 85;
		boolean spike = true;
				
		
		rose = new Rose(spike, length, etalonPrice, new FreshnessInteger(89));		
	}
	
	@Test
	public void getPriceFlowerTest(){
		
		Assert.assertEquals(etalonPrice, rose.getPrice(), deltaZero);
	}

}
