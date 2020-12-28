package book_library.core.validators;

import book_library.core.requests.SearchBooksRequest;
import book_library.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchBooksRequestValidator {

    public List<CoreError> validate (SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        return errors;
    }

    private List<CoreError> validateSearchFields(SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getTitle()) && isEmpty(request.getAuthor())){
            errors.add(new CoreError("title", "Must not be empty"));
            errors.add(new CoreError("author", "Must not be empty"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {return str == null || str.isEmpty();}
}
