/**
 * 
 */
package com.flowergarden.output;

import java.util.List;

import com.flowergarden.dao.model.BouquetImpl;

/**
 * @author Sergey Slipchenko
 *
 */
/*Interface holds all the common commands which you might need 
 * in order informatively prompt user via console output or any other while 
 * he works with the application*/
public interface OutputSpaceInt {
	
	public void printHello();
	
	public void printBouquets(List<BouquetImpl> listBouquets);

}
