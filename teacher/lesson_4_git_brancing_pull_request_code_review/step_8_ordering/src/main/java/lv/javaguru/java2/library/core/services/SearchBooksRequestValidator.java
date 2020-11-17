package lv.javaguru.java2.library.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.CoreError;

public class SearchBooksRequestValidator {

	public List<CoreError> validate(SearchBooksRequest request) {
		List<CoreError> errors = new ArrayList<>();
		errors.addAll(validateSearchFields(request));
		validateOrderBy(request).ifPresent(errors::add);
		validateOrderDirection(request).ifPresent(errors::add);
		validateMandatoryOrderBy(request).ifPresent(errors::add);
		validateMandatoryOrderDirection(request).ifPresent(errors::add);
		return errors;
	}

	private List<CoreError> validateSearchFields(SearchBooksRequest request) {
		List<CoreError> errors = new ArrayList<>();
		if (isEmpty(request.getTitle()) && isEmpty(request.getAuthor())) {
			errors.add(new CoreError("title", "Must not be empty!"));
			errors.add(new CoreError("author", "Must not be empty!"));
		}
		return errors;
	}

	private boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	private Optional<CoreError> validateOrderBy(SearchBooksRequest request) {
		return (request.getOrderBy() != null
				&& (request.getOrderBy().equals("author") || request.getOrderBy().equals("title")))
				? Optional.of(new CoreError("orderBy", "Must contain 'author' or 'title' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateOrderDirection(SearchBooksRequest request) {
		return (request.getOrderDirection() != null
				&& (request.getOrderDirection().equals("ASCENDING") || request.getOrderBy().equals("DESCENDING")))
				? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderBy(SearchBooksRequest request) {
		return (request.getOrderDirection() != null && request.getOrderBy() == null)
				? Optional.of(new CoreError("orderBy", "Must be not empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderDirection(SearchBooksRequest request) {
		return (request.getOrderBy() != null && request.getOrderDirection() == null)
				? Optional.of(new CoreError("orderDirection", "Must be not empty!"))
				: Optional.empty();
	}

}