/**
 * 
 */
package com.flowergarden.dao;

import org.junit.Rule;
import org.junit.Test;

import com.flowergarden.rules.CreateDbAndSampleDataRule;

/**
 * @author SOIERR
 *
 */
public class CreateDbAndSampleData {
	
	@Rule
	public CreateDbAndSampleDataRule createDbAndSampleData = new CreateDbAndSampleDataRule();
	
	@Test
	public void createDbAndSampleData(){
		
		System.out.println("Data has been prepared");
	}

}
