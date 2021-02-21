package lesson_12.core.services.reader;

import lesson_12.core.database.reader.ReaderRepository;
import lesson_12.core.domain.Reader;
import lesson_12.core.requests.reader.DeleteReaderRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.reader.DeleteReaderResponse;
import lesson_12.core.services.reader.validators.DeleteReaderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private DeleteReaderValidator validator;

    public DeleteReaderResponse deleteReaderResponse(DeleteReaderRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteReaderResponse(errors);
        }

        Reader reader = new Reader(request.getReaderFirstName(), request.getReaderLastName(), request.getReaderPersonalCode());
        boolean isRemoved = readerRepository.deleteReader(reader);
        return new DeleteReaderResponse(isRemoved);
    }
}
