/**
 * 
 */
package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.flowers.GeneralFlower;

/**
 * @author SOIERR
 *
 */
public interface DaoBouquetAlternative{
	
	<T extends Bouquet<? extends GeneralFlower>> T read();
	
	<T extends Bouquet<? extends GeneralFlower>> void save(T bouquet);

}
