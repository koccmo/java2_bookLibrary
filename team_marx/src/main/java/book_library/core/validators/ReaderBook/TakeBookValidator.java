package book_library.core.validators.ReaderBook;

import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TakeBookValidator {
    public List<CoreError> validate(TakeBookRequest request) {
        return new ArrayList<>();
    }
}
