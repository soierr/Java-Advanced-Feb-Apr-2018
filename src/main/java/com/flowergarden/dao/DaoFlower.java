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
	
	GeneralFlower2 getFlower(int flowerId);
	
	int create(GeneralFlower2 flower);
	void update(GeneralFlower2 newFlower);
	void delete(int flowerId);

}
