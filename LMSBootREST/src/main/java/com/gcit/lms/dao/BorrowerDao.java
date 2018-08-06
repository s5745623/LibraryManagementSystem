package com.gcit.lms.dao;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

import com.gcit.lms.entity.Borrower;

@Component
public class BorrowerDao extends BaseDao implements ResultSetExtractor<List<Borrower>> {
	
	public void addBorrower(Borrower borrower) throws SQLException {
		String   addBorrower  = "INSERT INTO tbl_borrower (name, address, phone) VALUE (?,?,?)";
		Object[] borrowerInfo = {borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone()};
		template.update(addBorrower, borrowerInfo);
	}
	
	public void updateBorrowerAddress(Borrower borrower) throws SQLException {
		String   updateBorrower = "UPDATE tbl_borrower SET address=? WHERE cardNo=?";
		Object[] borrowerInfo   = {borrower.getBorrowerAddress(), borrower.getBorrowerID()};
		template.update(updateBorrower, borrowerInfo);
	}
	
	public void updateBorrowerName(Borrower borrower) throws SQLException{
		String   updateBorrower = "UPDATE tbl_borrower SET name=? WHERE cardNo=?";
		Object[] borrowerInfo   = {borrower.getBorrowerName(), borrower.getBorrowerID()};
		template.update(updateBorrower, borrowerInfo);
	}
	
	public void updateBorrowerPhone(Borrower borrower) throws SQLException{
		String   updateBorrower = "UPDATE tbl_borrower SET phone=? WHERE cardNo=?";
		Object[] borrowerInfo   = {borrower.getBorrowerPhone(), borrower.getBorrowerID()};
		template.update(updateBorrower, borrowerInfo);
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException{
		String   deleteBorrower = "DELETE FROM tbl_borrower WHERE cardNo=?";
		Object[] borrowerInfo   = {borrower.getBorrowerID()};
		template.update(deleteBorrower, borrowerInfo);
	}
	
	public boolean checkBorrowerExist(Integer borrowerID) throws SQLException{
		String   readBorrower = "SELECT * FROM tbl_borrower WHERE cardNo=?";
		Object[] borrowerInfo = {borrowerID};
		return !template.query(readBorrower, borrowerInfo, this).isEmpty();
	}
	
	public List<Borrower> readAllBorrower() throws SQLException{
		String readBorrower = "SELECT * FROM tbl_borrower";
		return template.query(readBorrower, this);
	}
	
	public Borrower readBorrower(Integer borrowerID) throws SQLException{
		String   readBorrower = "SELECT * FROM tbl_borrower WHERE cardNo=?";
		Object[] borrowerInfo = {borrowerID};
		List<Borrower> borrower = template.query(readBorrower, borrowerInfo, this);
		if(borrower!=null && !borrower.isEmpty()){
			return borrower.get(0);
		}
		return null;
	}
	
	public List<Borrower> readBorrower(String borrowerName) throws SQLException{
		String  readBorrower = "SELECT * FROM tbl_publisher WHERE publisherName LIKE ?";
		Object[] borrowerInfo = {"%" + (borrowerName) + "%"};
		return template.query(readBorrower, borrowerInfo, this);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()){
			Borrower borrower = new Borrower();
			borrower.setBorrowerID(rs.getInt("cardNo"));
			borrower.setBorrowerName(rs.getString("name"));
			borrower.setBorrowerAddress(rs.getString("address"));
			borrower.setBorrowerPhone(rs.getString("phone"));
			borrowers.add(borrower);
		}
		return borrowers;
	}
}
