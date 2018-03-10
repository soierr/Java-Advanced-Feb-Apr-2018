/**
 * 
 */
package com.flowergarden.flowers.restricted;

import org.junit.Test;

/**
 * @author SOIERR
 *
 */
public class FlowerRestrictedColorTest {
	
	FlowerRestrictedColor flowerRestrictedColor = new FlowerRestrictedColor() {}; 
	
	@Test(expected = RuntimeException.class)
	public void getParameterTest(){
		
		flowerRestrictedColor.getParameter();
	}

}
