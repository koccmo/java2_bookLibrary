package lesson_12.core.services.reader;

import lesson_12.core.database.reader.ReaderRepository;
import lesson_12.core.requests.reader.DeleteReaderByFirstNameRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.reader.DeleteReaderByFirstNameResponse;
import lesson_12.core.services.reader.validators.DeleteReaderByFirstNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteReaderByFirstNameService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private DeleteReaderByFirstNameValidator validator;

    public DeleteReaderByFirstNameResponse deleteReaderByFirstNameResponse(DeleteReaderByFirstNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteReaderByFirstNameResponse(errors);
        }
        boolean isRemoved = readerRepository.deleteReaderByFirstName(request.getReaderFirstName());
        return new DeleteReaderByFirstNameResponse(isRemoved);
    }
}
