package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.gcit.lms.entity.Author;

@Component
public class AuthorDAO extends BaseDAO<Author> implements ResultSetExtractor<List<Author>>{
	
	public void createAuthor(Author author)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("INSERT INTO tbl_author (authorName) values (?)", new Object[] { author.getAuthorName() });
	}

	public void updateAuthor(Author author)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("update tbl_author set authorName =? where authorId = ?", new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void deleteAuthor(Author author)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		template.update("delete from tbl_author where authorId = ?", new Object[] { author.getAuthorId() });
	}

	public List<Author> readAllAuthors() throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_author", this);
	}

	public List<Author> readAllAuthorsByName(String searchString) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_author where authorName = ?", new Object[] { searchString }, this);
	}
	
	public List<Author> readAllAuthorsByBook(Integer bookId) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?", new Object[] { bookId }, this);
	}

	public Author readAuthorByPK(Integer primaryKey) throws ClassNotFoundException, SQLException {
		List<Author> authors = template.query("select * from tbl_author where authorId = ?", new Object[] { primaryKey }, this);
		if (!authors.isEmpty()) {
			return authors.get(0);
		}
		return null;
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}
}
