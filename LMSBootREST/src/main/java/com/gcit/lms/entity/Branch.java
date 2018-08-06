package com.gcit.lms.entity;

import java.util.HashMap;
import java.util.List;

public class Branch {
	

	private Integer branchID;
	private Integer bookID;
	private Integer copies;
	private String branchName;
	private String branchAddress;
	private List<Book> books;
	private HashMap<Integer, Integer> noOfCopies = new HashMap<>();
	
	public Integer getBranchID() {
		return branchID;
	}
	public void setBranchID(Integer branchID) {
		this.branchID = branchID;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public Integer getNoOfCopies(Integer bookID) {
		if (noOfCopies.containsKey(bookID)){
			return noOfCopies.get(bookID);
		}
		return null;
	}
	public void setNoOfCopies(Integer bookID, Integer copy) {
			noOfCopies.put(bookID, copy);
	}
	public Integer getBookID() {
		return bookID;
	}
	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}
	
	public Integer getCopies() {
		return copies;
	}
	public void setCopies(Integer copies) {
		this.copies = copies;
	}
	
}
