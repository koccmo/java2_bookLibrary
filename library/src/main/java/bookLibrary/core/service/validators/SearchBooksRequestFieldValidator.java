package bookLibrary.core.service.validators;

import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
@DIComponent
public class SearchBooksRequestFieldValidator {

    public List<CoreError> validate(SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getAuthor()) && isEmpty(request.getTitle())) {
            errors.add(new CoreError("Author", "Field Author Must be Fill UP!"));
            errors.add(new CoreError("Title", "Field Title Must Be Fill UP!"));
        }
        return errors;
    }


    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
