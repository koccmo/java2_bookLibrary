package electronic_library.core.services.reader;

import electronic_library.core.database.reader.ReaderRepository;
import electronic_library.core.requests.reader.DeleteReaderByFirstNameRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader.DeleteReaderByFirstNameResponse;
import electronic_library.core.services.reader.validators.DeleteReaderByFirstNameValidator;
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
