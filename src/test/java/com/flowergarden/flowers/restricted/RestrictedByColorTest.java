/**
 * 
 */
package com.flowergarden.flowers.restricted;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author SOIERR
 *
 */
/*Here we test all the newly created 
 * flowers against proper interfaces implementation*/
@RunWith(Parameterized.class)
public class RestrictedByColorTest {
	
	public RestrictedByColor restrictedByColor = null;
	public RestrictedBy<FlowerRestrictedColor.COLORS> restrictedBy = null;
	
	/**
	 * 
	 */
	public RestrictedByColorTest(RestrictedByColor restrictedByColor, RestrictedBy<FlowerRestrictedColor.COLORS> restrictedBy) {
		this.restrictedByColor = restrictedByColor;
		this.restrictedBy = restrictedBy;
	}
	
	@Test
	public void getParameterTest(){
		
		EnumSet<FlowerRestrictedColor.COLORS> es = EnumSet.allOf(FlowerRestrictedColor.COLORS.class);
		
		Assert.assertTrue(es.contains(restrictedByColor.getParameter()));	
		Assert.assertTrue(es.contains(restrictedBy.getParameter()));
		
	}
	
	@Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        
    	return Arrays.asList(new Object[]{new Carnation(), new Carnation()}, 
    						 new Object[]{new Lily(), new Lily()});
    }

}
