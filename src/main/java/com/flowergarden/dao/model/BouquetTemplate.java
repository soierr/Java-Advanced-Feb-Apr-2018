/**
 * 
 */
package com.flowergarden.dao.model;

/**
 * @author SOIERR
 *
 */
/*Bouquet template is an entity which may hold price of 
 * assembling, wrapping, etc, also, for example, isRestricted by color attribute then color set id, etc */
public class BouquetTemplate {
	
	private int id = 0;
	
	private String name = null;
	
	private long priceAssembling = 0L;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the assembledPrice
	 */
	public long getPriceAssembling(){
		return priceAssembling;
	}

	/**
	 * @param assembledPrice the assembledPrice to set
	 */
	public void setPriceAssembling(long priceAssembling) {
		this.priceAssembling = priceAssembling;
	}

}
