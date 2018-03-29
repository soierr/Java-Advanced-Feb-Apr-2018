/**
 * 
 */
package com.flowergarden.spring.ctx;

import javax.xml.stream.XMLStreamReader;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.flowergarden.run.Run;


/**
 * @author SOIERR
 *
 */
public class BeansTest {
	
	@Test
	public void beanWithArgumentCreationTest(){
		
		ApplicationContext ctx = Run.startContainer();
		
		XMLStreamReader xmlStreamReader= (XMLStreamReader) ctx.getBean("xmlStreamReader", "{sslipchenko: \"something\"}");
		
		Assert.assertNotNull(xmlStreamReader);
	}

}
