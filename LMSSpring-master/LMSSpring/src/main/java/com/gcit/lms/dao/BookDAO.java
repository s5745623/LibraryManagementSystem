package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;

public class BookDAO extends BaseDAO<Book> implements ResultSetExtractor<List<Book>>{

	public void createBook(Book book) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("INSERT INTO tbl_book (title) values (?)", new Object[]{ book.getTitle()});
	}
	
//	public Integer createBookWithPK(Book book) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		return saveWithPK("INSERT INTO tbl_book (title) values (?)", new Object[]{ book.getTitle()});
//	}

	public void updateBook(Book book) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("update tbl_book set title =? where bookId = ?", new Object[] { book.getTitle(), book.getBookId()});
	}

	public void deleteBook(Book book) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("delete from tbl_book where bookId = ?", new Object[]{book.getBookId()});
	}

	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_book", this);
	}
	
	public List<Book> readAllBooksByName(String searchString) throws ClassNotFoundException, SQLException{
		return template.query("select * from tbl_book where title = ?", new Object[]{searchString}, this);
	}
	
	public Book readBookByPK(Integer primaryKey) throws ClassNotFoundException, SQLException{
		List<Book> books= template.query("select * from tbl_book where bookId = ?", new Object[]{primaryKey}, this);
		if(!books.isEmpty()){
			return books.get(0);
		}
		return null;
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();	
		while(rs.next()){
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			books.add(book);
		}
		return books;
	}
	}
