/**
 * 
 */
package com.flowergarden.flowers.restricted;

/**
 * @author SOIERR
 *
 */
public interface RestrictedByColor extends RestrictedBy<FlowerRestrictedColor.COLORS>{
	
	public FlowerRestrictedColor.COLORS getParameter();
	
}
