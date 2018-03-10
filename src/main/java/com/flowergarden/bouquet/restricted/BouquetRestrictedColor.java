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

	public boolean put(T flower);
	
	public void setColorSet(Set<C> colorSet);
	
	public Set<C> getColorSet();

}
