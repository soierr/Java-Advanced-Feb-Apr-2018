/**
 * 
 */
package com.flowergarden.spring.ctx;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author SOIERR
 *
 */
@Configuration
@ComponentScan("com.flowergarden")
public class Beans {
	
	@Bean
	public Properties jdbcProperties(){
		
		return new PropertyLoader("jdbc.properties").getProperties();
	}
	
	@Bean
	public Properties sqlScripts(){
		
		return new PropertyLoader("sql-scripts.txt").getProperties();
	}
	
	@Bean
	public BasicDataSource dBPool(){
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(jdbcProperties().getProperty("jdbc.url"));
		
		return basicDataSource;
	}
}
