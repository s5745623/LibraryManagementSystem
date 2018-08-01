/**
 * 
 */
package com.gcit.lms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ppradhan
 *
 */
@Component
public abstract class BaseDAO<T> {

	@Autowired
	JdbcTemplate template;
}
