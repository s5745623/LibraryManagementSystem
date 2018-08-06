package com.gcit.lms.dao;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class CopiesDao extends BaseDao implements ResultSetExtractor<Integer> {
	
	public Integer readCopies(Integer bookID , Integer branchID) throws SQLException{
		String readBranch  = "SELECT * FROM tbl_book_copies WHERE branchId=? AND bookId=?";
		Object[] branchInfo = {branchID, bookID};
		Integer copies = template.query(readBranch, branchInfo, this);
		return copies;
	}
	
	@Override
	public Integer extractData(ResultSet rs) throws SQLException {
		Integer copies = 0;
		while(rs.next()){
			copies = rs.getInt("noOfCopies");
		}
		return copies;
	}

}