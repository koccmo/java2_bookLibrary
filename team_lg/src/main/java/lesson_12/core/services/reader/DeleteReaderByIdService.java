package lesson_12.core.services.reader;

import lesson_12.core.database.reader.ReaderRepository;
import lesson_12.core.requests.reader.DeleteReaderByIdRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.reader.DeleteReaderByIdResponse;
import lesson_12.core.services.reader.validators.DeleteReaderByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteReaderByIdService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private DeleteReaderByIdValidator validator;

    public DeleteReaderByIdResponse deleteReaderByIdResponse(DeleteReaderByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getReaderIdToDelete();
        if (!errors.isEmpty()) {
            return new DeleteReaderByIdResponse(errors);
        } else return new DeleteReaderByIdResponse(readerRepository.deleteReaderById(id));
    }
}
