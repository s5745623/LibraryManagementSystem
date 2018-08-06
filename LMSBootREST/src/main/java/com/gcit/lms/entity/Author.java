package com.gcit.lms.entity;
import java.util.*;

public class Author {

	
	private Integer authorID;
	private String  authorName;
	private List<Book> books;
	/**
	 * @return the authorID
	 */
	public Integer getAuthorID() {
		return authorID;
	}
	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthorID(Integer authorID) {
		this.authorID = authorID;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
