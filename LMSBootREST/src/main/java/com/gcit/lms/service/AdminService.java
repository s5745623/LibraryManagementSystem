package com.gcit.lms.service;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;
import java.sql.*;
import java.util.*;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

@RestController
public class AdminService {

	@Autowired
	BookDao 	 bookDao;
	
	@Autowired
	AuthorDao 	 authorDao;
	
	@Autowired
	PublisherDao publisherDao;
	
	@Autowired
	GenreDao 	 genreDao;
	
	@Autowired
	BranchDao 	 branchDao;
	
	@Autowired
	BorrowerDao  borrowerDao;
	
	@Autowired
	BookLoanDao  bookLoanDao;
	
	@Transactional
	@RequestMapping(value = "/admin/addAuthor", method = RequestMethod.POST, consumes="application/json")
	public void addAuthor(@RequestBody Author author) {
		try {
			authorDao.addAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	////buggy
	@Transactional
	@RequestMapping(value = "/admin/addBook", method = RequestMethod.POST, consumes="application/json")
	public void addBook(@RequestBody Book book){
			try {
				book.setBookId(bookDao.addBookReturnID(book));
				bookDao.addBook(book);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/addGenre", method = RequestMethod.POST, consumes="application/json")
	public void addGenre(@RequestBody Genre genre){
		try {
			genreDao.addGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/addPublisher", method = RequestMethod.POST, consumes="application/json")
	public void addPublisher(@RequestBody Publisher publisher){
		try {
			publisherDao.addPublisher(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/addBranch", method = RequestMethod.POST, consumes="application/json")
	public void addBranch(@RequestBody Branch branch){
		try {
			branchDao.addBranch(branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/addBorrower", method = RequestMethod.POST, consumes="application/json")
	public void addBorrower(@RequestBody Borrower borrower){
		try {
			borrowerDao.addBorrower(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/updateAuthor", method = RequestMethod.POST, consumes="application/json")
	public void updateAuthor(@RequestBody Author author){
		try {
			authorDao.updateAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/updateBook", method = RequestMethod.POST, consumes="application/json")
	public void updateBook(@RequestBody Book book){
			try {
				bookDao.updateBook(book);
				bookDao.deleteBookAuthors(book);
				bookDao.addBookAuthors(book);
				bookDao.deleteBookGenres(book);
				bookDao.addBookGenres(book);
				bookDao.updateBookPublisher(book);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/updateBookAndAuthor", method = RequestMethod.POST, consumes="application/json")
	public void updateOnlyBook(@RequestBody Book book){
			try {
				bookDao.updateBook(book);
				bookDao.deleteBookAuthors(book);
				bookDao.addBookAuthors(book);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/updateBookAndGenre", method = RequestMethod.POST, consumes="application/json")
	public void updateBookWGenres(@RequestBody Book book){
			try {
				bookDao.updateBook(book);
				bookDao.deleteBookGenres(book);
				bookDao.addBookGenres(book);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/updateBookAndPub", method = RequestMethod.POST, consumes="application/json")
	public void updateBookWPubs(@RequestBody Book book){
			try {
				bookDao.updateBook(book);
				bookDao.updateBookPublisher(book);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/updateGenre", method = RequestMethod.POST, consumes="application/json")
	public void updateGenre(@RequestBody Genre genre){
		try {
			genreDao.updateGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/updatePublisher", method = RequestMethod.POST, consumes="application/json")
	public void updatePublisher(@RequestBody Publisher publisher){
		try {
			publisherDao.updatePublisherName(publisher);
			publisherDao.updatePublisherAddress(publisher);
			publisherDao.updatePublisherPhone(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Transactional
	@RequestMapping(value = "/admin/updateBranch", method = RequestMethod.POST, consumes="application/json")
	public void updateBranch(@RequestBody Branch branch){
		try {
			branchDao.updateBranchName(branch);
			branchDao.updateBranchAddress(branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
 	
	@Transactional
	@RequestMapping(value = "/admin/updateBorrower", method = RequestMethod.POST, consumes="application/json")
	public void updateBorrower(@RequestBody Borrower borrower){
		try {
			borrowerDao.updateBorrowerName(borrower);
			borrowerDao.updateBorrowerAddress(borrower);
			borrowerDao.updateBorrowerPhone(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/deleteAuthor", method = RequestMethod.POST, consumes="application/json")
	public void deleteAuthor(@RequestBody Author author){
		try {
			authorDao.deleteAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/deleteBook", method = RequestMethod.POST, consumes="application/json")
	public void deleteBook(@RequestBody Book book){
		try {
			bookDao.deleteBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/deleteGenre", method = RequestMethod.POST, consumes="application/json")
	public void deleteGenre(@RequestBody Genre genre){
		try {
			genreDao.deleteGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/deletePublisher", method = RequestMethod.POST, consumes="application/json")
	public void deletePublisher(@RequestBody Publisher publisher){
		try {
			publisherDao.deletePublisher(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/admin/deleteBranch", method = RequestMethod.POST, consumes="application/json")
	public void deleteBranch(@RequestBody Branch branch){
		try {
			branchDao.deleteBranch(branch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/admin/deleteBorrower", method = RequestMethod.POST, consumes="application/json")
	public void deleteBorrower(@RequestBody Borrower borrower){
		try {
			borrowerDao.deleteBorrower(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//need 7 days
	@Transactional
	@RequestMapping(value = "/admin/updateDueDate", method = RequestMethod.POST, consumes="application/json")
	public void updateDueDate(@RequestBody BookLoan bookLoan){
		try {
			bookLoanDao.updateBookLoanDate(bookLoan);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/admin/readBooks", method = RequestMethod.GET, produces="application/json")
	public List<Book> readAllBook(){
		try {
			List<Book> books = bookDao.readAllBook();
			for (Book book : books){
				book.setAuthors(authorDao.readAuthorByBook(book.getBookId()));
				book.setGenres(genreDao.readGenreByBook(book.getBookId()));
				book.setPublisher(Arrays.asList(publisherDao.readPublisherByBook(book.getBookId())));
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readOneBook/{bookID}", method = RequestMethod.GET, produces="application/json")
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
	
	@RequestMapping(value = "/admin/readAuthors", method = RequestMethod.GET, produces="application/json")
	public List<Author> readAllAuthor(){
		try {
			List<Author> authors = authorDao.readAllAuthors();
			for (Author author : authors){
				author.setBooks(bookDao.readBookByAuthor(author.getAuthorID()));
				}
			return authors;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readAuthorsOnly", method = RequestMethod.GET, produces="application/json")
	public List<Author> readAllAuthorOnly(){
		try {
			List<Author> authors = authorDao.readAllAuthors();
			return authors;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/admin/readOneAuthor/{authorID}", method = RequestMethod.GET, produces="application/json")
	public Author readAuthor(@PathVariable Integer authorID){
		try {
			Author author = authorDao.readAuthor(authorID);
			author.setBooks(bookDao.readBookByAuthor(authorID));
			return author;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readAuthor/{authorName}", method = RequestMethod.GET, produces="application/json")
	public List<Author> readAuthor(@PathVariable  String authorName){
		try {
			List<Author> authors = authorDao.readAuthor(authorName);
			for (Author author : authors){
				author.setBooks(bookDao.readBookByAuthor(author.getAuthorID()));
				}
			return authors;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readGenres", method = RequestMethod.GET, produces="application/json")
	public List<Genre> readAllGenre(){
		try {
			List<Genre> genres = genreDao.readAllGenre();
			for (Genre genre : genres){
				genre.setBooks(bookDao.readBookByGenre(genre.getGenreId()));
				}
			return genres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readGenresOnly", method = RequestMethod.GET, produces="application/json")
	public List<Genre> readAllGenreOnly(){
		try {
			List<Genre> genres = genreDao.readAllGenre();
			return genres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readOneGenre/{genreID}", method = RequestMethod.GET, produces="application/json")
	public Genre readGenre(@PathVariable Integer genreID){
		try {
			Genre genre = genreDao.readGenre(genreID);
			genre.setBooks(bookDao.readBookByGenre(genreID));
			return genre;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readPublisher", method = RequestMethod.GET, produces="application/json")
	public List<Publisher> readAllPublisher(){
		try {
			List<Publisher> publishers = publisherDao.readAllPublisher();
			for (Publisher publisher : publishers){
				publisher.setBooks(bookDao.readBookByPublisher(publisher.getPublisherId()));
				}
			return publishers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readPublisherOnly", method = RequestMethod.GET, produces="application/json")
	public List<Publisher> readAllPublisherOnly(){
		try {
			List<Publisher> publishers = publisherDao.readAllPublisher();

			return publishers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readOnePublisher/{publisherID}", method = RequestMethod.GET, produces="application/json")
	public Publisher readPublisher(@PathVariable Integer publisherID){
		try {
			Publisher publisher = publisherDao.readPublisher(publisherID);
			publisher.setBooks(bookDao.readBookByPublisher(publisherID));
			return publisher;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readBranch", method = RequestMethod.GET, produces="application/json")
	public List<Branch> readAllBranch(){
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

	@RequestMapping(value = "/admin/readOneBranch/{branchID}", method = RequestMethod.GET, produces="application/json")
	public Branch readBranch(@PathVariable Integer branchID){	
		try {
			Branch branch = branchDao.readBranch(branchID);
			branch.setBooks(bookDao.readBookByBranch(branchID));
			return branch;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readBorrower", method = RequestMethod.GET, produces="application/json")
	public List<Borrower> readAllBorrower() {
		try {
			List<Borrower> borrowers = borrowerDao.readAllBorrower();
			return borrowers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readOneBorrower/{borrowerID}", method = RequestMethod.GET, produces="application/json")
	public Borrower readBorrower(@PathVariable Integer borrowerID) {
		try {
			Borrower borrower = borrowerDao.readBorrower(borrowerID);
			return borrower;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readBookLoans", method = RequestMethod.GET, produces="application/json")
	public List<BookLoan> readAllBookLoans(){
		try {
			List<BookLoan> bookLoans = bookLoanDao.readAllBookLoan();
			for (BookLoan bookLoan : bookLoans){
				bookLoan.setBook(bookDao.readBook(bookLoan.getBookID()));
				bookLoan.setBorrower(borrowerDao.readBorrower(bookLoan.getBorrowerID()));
				bookLoan.setBranch(branchDao.readBranch(bookLoan.getBranchID()));
				}
			return bookLoans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/readOneBookLoan", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public BookLoan readBookLoan(@RequestBody BookLoan bookLoan) {
		try {
			BookLoan bl = bookLoanDao.readBookLoan(bookLoan);
			bl.setBook(bookDao.readBook(bookLoan.getBookID()));
			bl.setBorrower(borrowerDao.readBorrower(bookLoan.getBorrowerID()));
			bl.setBranch(branchDao.readBranch(bookLoan.getBranchID()));
			return bl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/nullBookLoan", method = RequestMethod.GET, produces="application/json")
	public BookLoan nullBookLoan() {
		return new BookLoan();
	}

	@RequestMapping(value = "/admin/nullAuthor", method = RequestMethod.GET, produces="application/json")
	public Author nullAuthor() {
		return new Author();
	}
	
	@RequestMapping(value = "/admin/nullBorrower", method = RequestMethod.GET, produces="application/json")
	public Borrower nullBorrower() {
		return new Borrower();
	}
	
	@RequestMapping(value = "/admin/nullBook", method = RequestMethod.GET, produces="application/json")
	public Book nullBook() {
		return new Book();
	}
	
	@RequestMapping(value = "/admin/nullGenre", method = RequestMethod.GET, produces="application/json")
	public Genre nullGenre() {
		return new Genre();
	}
	
	@RequestMapping(value = "/admin/nullPublisher", method = RequestMethod.GET, produces="application/json")
	public Publisher nullPublisher() {
		return new Publisher();
	}
	
	@RequestMapping(value = "/admin/nullBranch", method = RequestMethod.GET, produces="application/json")
	public Branch initBranch() {
		return new Branch();
	}
	
}