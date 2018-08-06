package com.gcit.lms.dao;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

import com.gcit.lms.entity.Author;

@Component
public class AuthorDao extends BaseDao implements ResultSetExtractor<List<Author>> {
		
	public void addAuthor(Author author) throws SQLException {
		final String addAuthor   = "INSERT INTO tbl_author (authorName) VALUE (?)";
		Object[]     authorsInfo = {author.getAuthorName()};
		template.update(addAuthor, authorsInfo);
	}
	
	public void updateAuthor(Author author) throws SQLException{
		final String updateAuthor = "UPDATE tbl_author SET authorName = ? WHERE authorId = ?";
		Object[]     authorsInfo  = {author.getAuthorName(),author.getAuthorID()};
		template.update(updateAuthor, authorsInfo);
	}
	
	public void deleteAuthor(Author author) throws SQLException{
		final String deleteAuthor = "DELETE FROM tbl_author WHERE authorId = ?";
		Object[]     authorsInfo  = {author.getAuthorID()};
		template.update(deleteAuthor, authorsInfo);
	}
	
	public List<Author> readAllAuthors() throws SQLException{	
		final String readAuthor = "SELECT * FROM tbl_author";
		return template.query(readAuthor, this);
	}
	
	
	public Integer countAuthors() throws SQLException{
		String readAuthor = "SELECT * FROM tbl_author";
		return template.query(readAuthor, this).size();
	}
	
	public Author readAuthor(Integer authorID) throws SQLException{
		final String readAuthor  = "SELECT * FROM tbl_author WHERE authorId = ?";
		Object[]     authorsInfo = {authorID};
		List<Author> authors     = template.query(readAuthor, authorsInfo, this);
		if(authors!=null && !authors.isEmpty()){
			return authors.get(0);
		}
		return null;
	}
	
	public List<Author> readAuthor(String authorName) throws SQLException{
		final String readAuthor  = "SELECT * FROM tbl_author WHERE authorName LIKE ?";
		Object[] authorsInfo = {("%" + authorName + "%")};
		return template.query(readAuthor, authorsInfo, this);
	}
	
	public List<Author> readAuthorByBook(Integer bookID){
		final String readAuthor  = "SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)";
		Object[]     bookInfo = {(bookID)};
		return template.query(readAuthor, bookInfo, this);
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author author = new Author();
			author.setAuthorID(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}

}