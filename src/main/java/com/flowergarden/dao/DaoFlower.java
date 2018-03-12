/**
 * 
 */
package com.flowergarden.dao;

import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
public interface DaoFlower {
	
	public GeneralFlower2 getFlower(int flowerId);
	
	public int create(GeneralFlower2 flower);
	public void update(GeneralFlower2 newFlower);
	public void delete(int flowerId);

}
