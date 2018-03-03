/**
 * 
 */
package com.flowergarden.run;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

/**
 * @author SOIERR
 *
 */
public class GetPriceFlowerTest {
	
	private final float etalonPrice = 50f;
	private final float deltaZero = 0f;
	private Rose rose = null;
	
	@Before
	public void setFlowerPrice(){
		
		 rose = new Rose(true, 85, etalonPrice, new FreshnessInteger(89));
		
	}
	
	@Test
	public void getFlowerPriceTest(){
		
		Assert.assertEquals(etalonPrice, rose.getPrice(), deltaZero);
	}

}
