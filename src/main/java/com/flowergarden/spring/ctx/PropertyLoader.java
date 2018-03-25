/**
 * 
 */
package com.flowergarden.spring.ctx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author SOIERR
 *
 */
public class PropertyLoader {
	
	private final String PROPERTIES;
	
	Properties props = new Properties();

	public PropertyLoader(String filename) {
		
		this.PROPERTIES = filename;

		InputStream input = null;

		try {
			
	        ClassLoader loader = Thread.currentThread().getContextClassLoader();			
			input = loader.getResourceAsStream(PROPERTIES);			
			props.load(input);

		} catch (IOException ex) {
			
			ex.printStackTrace();
			
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public Properties getProperties(){
		
		return this.props;
	}
}
