package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.ReaderBookRepository;
import bookLibrary.core.request.TakeBookRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TakeBookTokenBook {
    @Autowired
    private ReaderBookRepository repository;

    public List<CoreError> validate(TakeBookRequest request) {
        List<CoreError> errors =  new ArrayList<>();
        bookTokenFromLibrary(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> bookTokenFromLibrary(TakeBookRequest request) {
        boolean bookToken = repository.checkReturnDateEmpty(Long.valueOf(request.getBookId()));
        return (bookToken)
                ? Optional.empty()
                : Optional.of(new CoreError("Book", "Token from Library"));
    }
}
