/**
 * 
 */
package com.flowergarden.bouquet.restricted;

import java.util.Set;

/**
 * @author SOIERR
 *
 */
public interface BouquetRestrictedColor <T, C>{

	boolean put(T flower);
	
	void setColorSet(Set<C> colorSet);
	
	Set<C> getColorSet();

}
