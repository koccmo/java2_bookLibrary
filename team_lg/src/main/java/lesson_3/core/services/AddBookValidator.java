package lesson_3.core.services;

import lesson_3.core.requests.AddBookRequest;
import lesson_3.core.responses.CoreError;

import java.math.BigDecimal;
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

        BigDecimal bookPrice = request.getBookPrice();
        if (bookPrice.compareTo(BigDecimal.ZERO) == 0) {
            errors.add(new CoreError("bookPrice", "Must not be zero!"));
        }
        return errors;
    }
}
