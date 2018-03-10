/**
 * 
 */
package com.flowergarden.flowers.restricted;

import com.flowergarden.flowers.GeneralFlower;

/**
 * @author SOIERR
 *
 */
/*This is an implementation which allow us to have backward compatibility 
 * i.e. newly created flowers might be stored in legacy unrestricted bouquet*/
public abstract class FlowerRestrictedColor extends GeneralFlower implements RestrictedByColor{
	
	public enum COLORS{
		
		RED,
		WHITE,
		BLUE
	}

	@Override
	public COLORS getParameter() {
		
		throw new UnsupportedOperationException("The method needs to be overriden");
	}

}
