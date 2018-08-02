/**
 * 
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

/**
 * @author ppradhan
 *
 */
@RestController
public class AdminService {

	@Autowired
	AuthorDAO adao;

	@Autowired
	BookDAO bdao;

	@Transactional
	@RequestMapping(value = "/updateAuthor", method = RequestMethod.POST, consumes = "application/json")
	public List<Author> saveAuthor(@RequestBody Author author) throws SQLException {
		List<Author> authors = new ArrayList<>();
		try {
			if (author.getAuthorId() != null && author.getAuthorName() != null) {
				adao.updateAuthor(author);
			} else if (author.getAuthorId() != null) {
				adao.deleteAuthor(author);
			} else {
				adao.createAuthor(author);
			}
			authors = adao.readAllAuthors();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return authors;
	}

	@RequestMapping(value = "/readBooks", method = RequestMethod.GET, produces = "application/json")
	public List<Book> readBooks(@RequestParam("title") String title) throws SQLException {
		List<Book> books = new ArrayList<>();
		try {
			if (!title.isEmpty()) {
				books = bdao.readAllBooksByName(title);
			} else {
				books = bdao.readAllBooks();
			}
			for (Book b : books) {
				b.setAuthors(adao.readAllAuthorsByBook(b.getBookId()));
			}
			// do the same for genre/publ/branch etc
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return books;
	}

	@RequestMapping(value = "/readBooksByName/{title}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> readBooksByTitle(@PathVariable String title) throws SQLException {
		List<Book> books = new ArrayList<>();
		try {
			books = bdao.readAllBooksByName(title);
			for (Book b : books) {
				b.setAuthors(adao.readAllAuthorsByBook(b.getBookId()));
			}
			// do the same for genre/publ/branch etc
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return books;
	}

}
