package lesson_12.core.services.reader;

import lesson_12.core.database.reader.ReaderRepository;
import lesson_12.core.requests.reader.DeleteReaderByLastNameRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.reader.DeleteReaderByLastNameResponse;
import lesson_12.core.services.reader.validators.DeleteReaderByLastNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteReaderByLastNameService {
    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private DeleteReaderByLastNameValidator validator;

    public DeleteReaderByLastNameResponse deleteReaderByLastNameResponse(DeleteReaderByLastNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteReaderByLastNameResponse(errors);
        }
        boolean isRemoved = readerRepository.deleteReaderByLastName(request.getReaderLastName());
        return new DeleteReaderByLastNameResponse(isRemoved);
    }
}
