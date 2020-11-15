package book_library.core.services;

import book_library.core.requests.AddBookRequest;
import book_library.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

public class AddBookValidator {

    public List<CoreError> validate(AddBookRequest request) {
        List<CoreError> errors = new ArrayList<>();

        String bookTitle = request.getBookTitle();
        if (bookTitle == null || bookTitle.isEmpty()) {
            errors.add(new CoreError("bookTitle", "Must not be empty!"));
        }

        String bookAuthor = request.getBookAuthor();
        if (bookAuthor == null || bookAuthor.isEmpty()) {
            errors.add(new CoreError("bookAuthor", "Must not be empty!"));
        }

        return errors;
    }
}
