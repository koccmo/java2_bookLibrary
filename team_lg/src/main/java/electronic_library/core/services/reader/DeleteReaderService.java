package electronic_library.core.services.reader;

import electronic_library.core.database.reader.ReaderRepository;
import electronic_library.core.domain.Reader;
import electronic_library.core.requests.reader.DeleteReaderByFirstNameRequest;
import electronic_library.core.requests.reader.DeleteReaderRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader.DeleteReaderByFirstNameResponse;
import electronic_library.core.responses.reader.DeleteReaderResponse;
import electronic_library.core.services.reader.validators.DeleteReaderByFirstNameValidator;
import electronic_library.core.services.reader.validators.DeleteReaderValidator;
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
