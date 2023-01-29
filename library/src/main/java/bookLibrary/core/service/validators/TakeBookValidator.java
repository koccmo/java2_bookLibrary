package bookLibrary.core.service.validators;

import bookLibrary.core.request.TakeBookRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TakeBookValidator {
    @Autowired
    private TakeBookReaderIdValidator readerIdValidator;
    @Autowired
    private TakeBookBookIdValidator bookIdValidator;
    @Autowired
    private TakeBookTokenBook tokenBook;

    public List<CoreError> validate(TakeBookRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(readerIdValidator.validate(request));
        errors.addAll(bookIdValidator.validate(request));
        if (errors.isEmpty()) {
            errors.addAll(tokenBook.validate(request));
        }
        return errors;
    }
}
