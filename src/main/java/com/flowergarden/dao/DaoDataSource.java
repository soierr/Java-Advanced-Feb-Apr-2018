/**
 * 
 */
package com.flowergarden.dao;

import java.sql.Connection;

/**
 * @author SOIERR
 *
 */
public interface DaoDataSource {

	Connection getConnection();
}
