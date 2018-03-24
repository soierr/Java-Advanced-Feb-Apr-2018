/**
 * 
 */
package com.flowergarden.bouquet;

/**
 * @author SOIERR
 *
 */
/*The price class will allow us to avoid any changes in DAO layer 
 * once one the component of total price is changed/added.
 * For examlple in future the total price might include delivery price, extra material price, etc 
 * Therefore Open/Close principle conformed*/
public class Price {
	
	private long priceAssembling = 0L;
	
	private long priceFlowers = 0L;
	
	/**
	 * 
	 */
	public Price() {
		
	}
	
	public Price(long priceAssembling) {
		this.priceAssembling = priceAssembling;
	}
	
	/**
	 * 
	 */
	public Price(long priceAssembling, long priceFlowers) {
		
		this.priceAssembling = priceAssembling;
		this.priceFlowers = priceFlowers;
		
	}

	/**
	 * @return the priceAssembling
	 */
	public long getPriceAssembling() {
		return priceAssembling;
	}

	/**
	 * @param priceAssembling the priceAssembling to set
	 */
	public void setPriceAssembling(long priceAssembling) {
		this.priceAssembling = priceAssembling;
	}

	/**
	 * @return the priceTotal
	 */
	public long getPriceTotal() {
		return priceAssembling + priceFlowers;
	}

	/**
	 * @return the priceFlowers
	 */
	public long getPriceFlowers() {
		return priceFlowers;
	}

	/**
	 * @param priceFlowers the priceFlowers to set
	 */
	public void setPriceFlowers(long priceFlowers) {
		this.priceFlowers = priceFlowers;
	}

}
