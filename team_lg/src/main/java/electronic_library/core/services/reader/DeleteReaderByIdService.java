package electronic_library.core.services.reader;

import electronic_library.core.database.reader.ReaderRepository;
import electronic_library.core.requests.reader.DeleteReaderByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader.DeleteReaderByIdResponse;
import electronic_library.core.services.reader.validators.DeleteReaderByIdValidator;
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
