package com.gcit.lms.service;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

@RestController
public class BorrowerService {
	
	@Autowired
	BookDao 	 bookDao;
	
	@Autowired
	BranchDao 	 branchDao;
	
	@Autowired
	AuthorDao 	 authorDao;
	
	@Autowired
	GenreDao 	 genreDao;
	
	@Autowired
	PublisherDao publisherDao;
	
	@Autowired
	BorrowerDao  borrowerDao;
	
	@Autowired
	BookLoanDao  bookLoanDao;
	
	@Autowired
	CopiesDao	copiesDao;
	
	@RequestMapping(value = "/borrower/readBranch", method = RequestMethod.GET, produces="application/json")
	public List<Branch> readAllBranchs(){
		try {
			List<Branch> branchs = branchDao.readAllBranch();
			for (Branch branch : branchs){
				branch.setBooks(bookDao.readBookByBranch(branch.getBranchID()));
				}
			return branchs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/borrower/readOneBranchCon/{branchID}", method = RequestMethod.GET, produces="application/json")
	public Branch readBranchWithLimit(@PathVariable Integer branchID){
		try {
			
			List<Book> books = bookDao.readBookByBranchCon(branchID);
			for (Book book : books){
				book.setAuthors(authorDao.readAuthorByBook(book.getBookId()));
				book.setGenres(genreDao.readGenreByBook(book.getBookId()));
				book.setPublisher(Arrays.asList(publisherDao.readPublisherByBook(book.getBookId())));
				Integer copies = copiesDao.readCopies(book .getBookId(), branchID);
				book.setRelatedCopies(copies);
			}
			
			Branch branch = branchDao.readBranch(branchID);
			branch.setBooks(books);
			for (Book book : branch.getBooks()){
				Integer copies = copiesDao.readCopies(book.getBookId(), branchID);
				branch.setNoOfCopies(book.getBookId(), copies);
			}
			return branch;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/borrower/readOneBranch/{branchID}", method = RequestMethod.GET, produces="application/json")
	public Branch readBranch(@PathVariable Integer branchID){	
		try {
			Branch branch = branchDao.readBranch(branchID);
			branch.setBooks(bookDao.readBookByBranch(branchID));
			for (Book book : branch.getBooks()){
				Integer copies = copiesDao.readCopies(book.getBookId(), branchID);
				book.setRelatedCopies(copies);
				branch.setNoOfCopies(book.getBookId(), copies);
			}
			return branch;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/borrower/readOneBook/{bookID}", method = RequestMethod.GET, produces="application/json")
	public Book readBook(@PathVariable Integer bookID){
		try {
			Book book = bookDao.readBook(bookID);
			book.setAuthors(authorDao.readAuthorByBook(bookID));
			book.setGenres(genreDao.readGenreByBook(bookID));
			book.setPublisher(Arrays.asList(publisherDao.readPublisherByBook(book.getBookId())));
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/borrower/checkOutBook", method = RequestMethod.POST, consumes="application/json")
	public void checkOutBook(@RequestBody BookLoan bookLoan){
		try {
			bookLoanDao.addBookLoan(bookLoan);
			branchDao.decNoOfCopies(readBranchWithLimit(bookLoan.getBranchID()),bookLoan.getBookID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/borrower/returnBook", method = RequestMethod.POST, consumes="application/json")
	public void returnBook(@RequestBody BookLoan bookLoan){
		try {
			bookLoanDao.returnBookLoanDate(bookLoan);
			Branch branch = readBranch(bookLoan.getBranchID());
			branchDao.addNoOfCopies(branch, bookLoan.getBookID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/borrower/readOneBorrower/{borrowerID}", method = RequestMethod.GET, produces="application/json")
	public Borrower readBorrower(@PathVariable Integer borrowerID) {
		try {
			Borrower borrower = borrowerDao.readBorrower(borrowerID);
			return borrower;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/borrower/readOneBookLoan/{borrowerID}", method = RequestMethod.GET, produces="application/json")
	public List<BookLoan> readBookLoan(@PathVariable Integer borrowerID){
		try {
			List<BookLoan> bookLoans = bookLoanDao.readBookLoan(borrowerID);
			for (BookLoan bookLoan : bookLoans){
				Book book = bookDao.readBook(bookLoan.getBookID());
				Branch branch = readBranch(bookLoan.getBranchID());
				Integer copies = copiesDao.readCopies(book.getBookId(), branch.getBranchID());
				book.setRelatedCopies(copies);
				branch.setNoOfCopies(book.getBookId(), copies);
				bookLoan.setBook(book);
				bookLoan.setBranch(branch);
			}
			return bookLoans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/borrower/nullBookLoan", method = RequestMethod.GET, produces="application/json")
	public BookLoan nullBookLoan() {
		return new BookLoan();
	}

	
}