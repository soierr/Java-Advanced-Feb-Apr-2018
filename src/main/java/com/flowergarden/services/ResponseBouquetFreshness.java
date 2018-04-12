/**
 * 
 */
package com.flowergarden.services;

import java.util.ArrayList;
import java.util.List;

import com.flowergarden.flowers.GeneralFlower2;

/**
 * @author SOIERR
 *
 */
public class ResponseBouquetFreshness{
	
	public enum RESULT{
		
		OK_ALL,
		OK_SPECIFIC,
		NOK_ALL,
		NOT_FOUND
	}
	
	private RESULT result;
	
	private List<GeneralFlower2> listFlowersUpdated = new ArrayList<GeneralFlower2>();
	
	private List<GeneralFlower2> listFlowersZeroPrice = new ArrayList<GeneralFlower2>();
	
	/**
	 * 
	 */
	public ResponseBouquetFreshness() {	
	}
	
	/**
	 * 
	 */
	public ResponseBouquetFreshness(RESULT result) {
		
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public RESULT getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(RESULT result) {
		this.result = result;
	}

	/**
	 * @return the listFlowersUpdated
	 */
	public List<GeneralFlower2> getListFlowersUpdated() {
		return listFlowersUpdated;
	}

	/**
	 * @param listFlowersUpdated the listFlowersUpdated to set
	 */
	public void setListFlowersUpdated(List<GeneralFlower2> listFlowersUpdated) {
		this.listFlowersUpdated = listFlowersUpdated;
	}

	/**
	 * @return the listFlowersZeroPrice
	 */
	public List<GeneralFlower2> getListFlowersZeroPrice() {
		return listFlowersZeroPrice;
	}

	/**
	 * @param listFlowersZeroPrice the listFlowersZeroPrice to set
	 */
	public void setListFlowersZeroPrice(List<GeneralFlower2> listFlowersZeroPrice) {
		this.listFlowersZeroPrice = listFlowersZeroPrice;
	}
	


}
