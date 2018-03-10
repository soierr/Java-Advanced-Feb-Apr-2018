/**
 * 
 */
package com.flowergarden.rules;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.flowergarden.dao.DaoBouquetTemplateTest;
import com.flowergarden.dao.DaoBouquetTest;
import com.flowergarden.dao.DaoFlowerImplTest;

/**
 * @author SOIERR
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	/*Put needed db related test here*/
	DaoBouquetTemplateTest.class,
	DaoBouquetTest.class,
	DaoFlowerImplTest.class
	
})
/*
 * ClassRuleAddingTest.createDropDbObjectsRule.getConnection() 
 * needs to be added in any test of the suite to get the connection*/
public class SuiteHolderClassRuleSQL {
	
	
	@ClassRule
	public static CreateDbAndSampleDataRule createDropDbObjectsRule = new CreateDbAndSampleDataRule();
	
}
