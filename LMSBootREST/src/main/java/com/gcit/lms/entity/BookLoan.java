package com.gcit.lms.entity;

import java.sql.Timestamp;

public class BookLoan {

	private Book book;
	private Integer bookID;
	private Borrower borrower;
	private Integer borrowerID;
	private Branch branch;
	private Integer branchID;
	private Timestamp dateOut;
	private Timestamp dueDate;
	private Timestamp dateIn;
	
	public Integer getBookID() {
		return bookID;
	}
	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}
	public Integer getBorrowerID() {
		return borrowerID;
	}
	public void setBorrowerID(Integer borrowerID) {
		this.borrowerID = borrowerID;
	}
	public Integer getBranchID() {
		return branchID;
	}
	public void setBranchID(Integer branchID) {
		this.branchID = branchID;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Timestamp getDateOut() {
		return dateOut;
	}
	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}
	public Timestamp getDueDate() {
		return dueDate;
	}
	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}
	public Timestamp getDateIn() {
		return dateIn;
	}
	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}
	
}
