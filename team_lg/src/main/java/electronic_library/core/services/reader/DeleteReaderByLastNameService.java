package electronic_library.core.services.reader;

import electronic_library.core.database.reader.ReaderRepository;
import electronic_library.core.requests.reader.DeleteReaderByLastNameRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader.DeleteReaderByLastNameResponse;
import electronic_library.core.services.reader.validators.DeleteReaderByLastNameValidator;
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
