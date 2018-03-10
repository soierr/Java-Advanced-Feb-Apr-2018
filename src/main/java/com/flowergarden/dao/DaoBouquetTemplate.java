/**
 * 
 */
package com.flowergarden.dao;

import com.flowergarden.dao.model.BouquetTemplate;

/**
 * @author SOIERR
 *
 */
public interface DaoBouquetTemplate {
	
	public int createBouquetTemplate(BouquetTemplate template);	
	public void updateBouquetTemplate(BouquetTemplate template);	
	public void deleteBouquetTemplate(int templateId);

}
