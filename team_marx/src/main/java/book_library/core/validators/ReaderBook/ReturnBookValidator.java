package book_library.core.validators.ReaderBook;

import book_library.core.requests.ReaderBook.ReturnBookRequest;
import book_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReturnBookValidator {
    public List<CoreError> validate(ReturnBookRequest request) {
        return new ArrayList<>();
    }
}
