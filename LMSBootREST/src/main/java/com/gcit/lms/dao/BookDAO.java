package com.gcit.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.gcit.lms.entity.Book;

@Component
public class BookDAO extends BaseDAO<Book> implements ResultSetExtractor<List<Book>> {

	public void createBook(Book book)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("INSERT INTO tbl_book (title) values (?)", new Object[] { book.getTitle() });
	}

	public Integer addBookWithID(Book book) throws ClassNotFoundException, SQLException {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(con -> {
			PreparedStatement ps = con.prepareStatement("insert into tbl_book (title) values (?)", new String[] { "bookId" });
			ps.setString(1, book.getTitle());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKeys().get("bookId");
	}

	public void updateBook(Book book)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("update tbl_book set title =? where bookId = ?",
				new Object[] { book.getTitle(), book.getBookId() });
	}

	public void deleteBook(Book book)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("delete from tbl_book where bookId = ?", new Object[] { book.getBookId() });
	}

	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_book", this);
	}

	public List<Book> readAllBooksByName(String searchString) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_book where title = ?", new Object[] { searchString }, this);
	}

	public Book readBookByPK(Integer primaryKey) throws ClassNotFoundException, SQLException {
		List<Book> books = template.query("select * from tbl_book where bookId = ?", new Object[] { primaryKey }, this);
		if (!books.isEmpty()) {
			return books.get(0);
		}
		return null;
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			books.add(book);
		}
		return books;
	}
}
