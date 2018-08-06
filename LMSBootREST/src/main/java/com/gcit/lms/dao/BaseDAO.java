package com.gcit.lms.dao;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseDao {
	
	@Autowired
	public JdbcTemplate template;

}
