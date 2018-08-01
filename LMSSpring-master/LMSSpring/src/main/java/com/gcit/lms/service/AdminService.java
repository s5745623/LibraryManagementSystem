/**
 * 
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

/**
 * @author ppradhan
 *
 */
public class AdminService {

	@Autowired
	AuthorDAO adao;

	@Autowired
	BookDAO bdao;

	@Transactional
	public void saveAuthor(Author author) throws SQLException {
		try {
			if (author.getAuthorId() != null && author.getAuthorName() != null) {
				adao.updateAuthor(author);
			} else if (author.getAuthorId() != null) {
				adao.deleteAuthor(author);
			} else {
				adao.createAuthor(author);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> readBooks() throws SQLException{
		List<Book> books = new ArrayList<>();
		try {
			books = bdao.readAllBooks();
			for(Book b: books){
				b.setAuthors(adao.readAllAuthorsByBook(b.getBookId()));
			}
			//do the same for genre/publ/branch etc
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return books;
	}

}
