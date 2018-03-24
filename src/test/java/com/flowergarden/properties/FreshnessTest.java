/**
 * 
 */
package com.flowergarden.properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author SOIERR
 *
 */
public class FreshnessTest {
	
	int sampleFreshness = 55;
	
	Freshness<Integer> freshness = new FreshnessInteger(sampleFreshness);
	
	@Test
	public void getFreshnessTest(){
		
		Assert.assertTrue(freshness.getFreshness() == sampleFreshness);;
	}
	
	@Test
	public void sortByFreshnessTest(){
		
		int sampleBetterFreshness = 99;
		
		Freshness<Integer> freshnessBetter = new FreshnessInteger(sampleBetterFreshness);
		
		Assert.assertTrue(((FreshnessInteger)freshness).compareTo((FreshnessInteger)freshnessBetter) == -1);;
	}

}
