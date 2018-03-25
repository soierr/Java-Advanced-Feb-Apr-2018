/**
 * 
 */
package com.flowergarden.flowers.restricted;

/**
 * @author SOIERR
 *
 */
public interface RestrictedByColor extends RestrictedBy<FlowerRestrictedColor.COLORS>{
	
	FlowerRestrictedColor.COLORS getParameter();
	
}
