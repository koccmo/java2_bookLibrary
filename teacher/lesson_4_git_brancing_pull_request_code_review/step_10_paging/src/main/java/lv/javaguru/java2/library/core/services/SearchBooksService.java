package lv.javaguru.java2.library.core.services;

import java.util.Comparator;
import java.util.List;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.requests.Ordering;
import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.SearchBooksResponse;

public class SearchBooksService {

	private Database database;
	private SearchBooksRequestValidator validator;

	public SearchBooksService(Database database,
							  SearchBooksRequestValidator validator) {
		this.database = database;
		this.validator = validator;
	}

	public SearchBooksResponse execute(SearchBooksRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new SearchBooksResponse(null, errors);
		}

		List<Book> books = null;
		if (request.isTitleProvided() && !request.isAuthorProvided()) {
			books = database.findByTitle(request.getTitle());
		}
		if (!request.isTitleProvided() && request.isAuthorProvided()) {
			books = database.findByAuthor(request.getAuthor());
		}
		if (request.isTitleProvided() && request.isAuthorProvided()) {
			books = database.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
		}

		if (request.getOrdering() != null) {
			Ordering ordering = request.getOrdering();
			Comparator<Book> comparator = ordering.getOrderBy().equals("title")
					? Comparator.comparing(Book::getTitle)
					: Comparator.comparing(Book::getAuthor);
			if (ordering.getOrderDirection().equals("DESCENDING")) {
				comparator = comparator.reversed();
			}
			books.sort(comparator);
		}

		return new SearchBooksResponse(books, null);
	}

}
