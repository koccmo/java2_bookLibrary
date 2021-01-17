package lv.javaguru.java2.library.core.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.library.core.domain.Book;

@Component
public class JdbcDatabaseImpl implements Database {

	@Autowired private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Book book) {
		jdbcTemplate.update(
				"INSERT INTO books (title, author) "
						+ "VALUES (?, ?)",
				book.getTitle(), book.getAuthor()
		);
	}

	@Override
	public boolean deleteById(Long id) {
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		return null;
	}

	@Override
	public List<Book> findByTitle(String title) {
		return null;
	}

	@Override
	public List<Book> findByAuthor(String author) {
		return null;
	}

	@Override
	public List<Book> findByTitleAndAuthor(String title, String author) {
		return null;
	}
}
