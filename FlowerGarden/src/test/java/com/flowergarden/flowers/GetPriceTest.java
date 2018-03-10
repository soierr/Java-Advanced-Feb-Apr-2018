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
public class GetPriceTest {
	
	private final float etalonPrice = 50f;
	private final float deltaZero = 0f;
	private Rose rose = null;
	
	
	
	@Before
	public void prepareFlowerPrice(){
		
		rose = new Rose(true, 85, etalonPrice, new FreshnessInteger(89));		
	}
	
	@Test
	public void getPriceFlowerTest(){
		
		Assert.assertEquals(etalonPrice, rose.getPrice(), deltaZero);
	}

}
