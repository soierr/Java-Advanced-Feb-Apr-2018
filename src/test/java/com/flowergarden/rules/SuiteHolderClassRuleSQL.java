/**
 * 
 */
package com.flowergarden.rules;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * @author SOIERR
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

	
	
})
/*
 * ClassRuleAddingTest.createDropDbObjectsRule.getConnection() 
 * needs to be added in any test of the suite to get the connection*/
public class SuiteHolderClassRuleSQL {
	
	
	@ClassRule
	public static CreateDropDbObjectsRule createDropDbObjectsRule = new CreateDropDbObjectsRule();
	
}
