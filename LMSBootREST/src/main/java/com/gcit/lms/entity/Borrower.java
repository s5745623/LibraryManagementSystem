package com.gcit.lms.entity;


public class Borrower {
	
	private Integer borrowerID;
	private String  borrowerName;
	private String  borrowerAddress;
	private String  borrowerPhone;

	public Integer getBorrowerID() {
		return borrowerID;
	}
	public void setBorrowerID(Integer borrowerID) {
		this.borrowerID = borrowerID;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getBorrowerAddress() {
		return borrowerAddress;
	}
	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}
	public String getBorrowerPhone() {
		return borrowerPhone;
	}
	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}

	
}
