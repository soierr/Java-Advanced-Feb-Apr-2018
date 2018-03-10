/**
 * 
 */
package com.flowergarden.rules;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.flowergarden.properties.SortByFreshnessMockTest;
import com.flowergarden.properties.SortByFreshnessNPETest;
import com.flowergarden.properties.SortByFreshnessTest;

/**
 * @author SOIERR
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

	SortByFreshnessMockTest.class,
	SortByFreshnessNPETest.class,
	SortByFreshnessTest.class
	
})
/*
 * ClassRuleAddingTest.createDropDbObjectsRule.getConnection() 
 * needs to be added in any test of the suite to get the connection*/
public class ClassRuleAddingTest {
	
	
	@ClassRule
	public static CreateDbAndSampleDataRule createDropDbObjectsRule = new CreateDbAndSampleDataRule();
	
}
