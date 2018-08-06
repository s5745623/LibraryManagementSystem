package com.gcit.lms.entity;

import java.util.List;

public class Book {
	
	private Integer bookId;
	private String title;
	private List<Author> authors;
	private List<Integer> authorID;
	private List<Publisher> publisher;
	private Integer publisherID;
	private List<Genre> genres;
	private List<Integer> genreID;
	private Integer relatedCopies;
	

	public List<Integer> getAuthorID() {
		return authorID;
	}
	public void setAuthorID(List<Integer> authorID) {
		this.authorID = authorID;
	}
	public Integer getPublisherID() {
		return publisherID;
	}
	public void setPublisherID(Integer publisherID) {
		this.publisherID = publisherID;
	}
	public List<Integer> getGenreID() {
		return genreID;
	}
	public void setGenreID(List<Integer> genreID) {
		this.genreID = genreID;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<Publisher> getPublisher() {
		return publisher;
	}
	public void setPublisher(List<Publisher> publisher) {
		this.publisher = publisher;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Integer getRelatedCopies() {
		return relatedCopies;
	}
	public void setRelatedCopies(Integer relatedCopies) {
		this.relatedCopies = relatedCopies;
	}

}
