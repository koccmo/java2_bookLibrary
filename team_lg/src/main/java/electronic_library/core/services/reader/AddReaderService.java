package electronic_library.core.services.reader;

import electronic_library.core.database.reader.ReaderRepository;
import electronic_library.core.domain.Reader;
import electronic_library.core.requests.reader.AddReaderRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader.AddReaderResponse;
import electronic_library.core.services.reader.validators.AddReaderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private AddReaderValidator validator;

    public AddReaderResponse execute(AddReaderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddReaderResponse(errors);
        }

        Reader reader = new Reader(request.getReaderFirstName(), request.getReaderLastName(),
                request.getReaderPersonalCode(), request.getReaderPhoneNumber(), request.getReaderEmail(), request.getReaderAddress());

        if (readerRepository.containsReader(getReader(reader))) {
            errors.add(new CoreError("database", "Database contains the this reader"));
            return new AddReaderResponse(errors);
        } else {
            readerRepository.saveReader(getReader(reader));
            return new AddReaderResponse(reader);
        }
    }

    private Reader getReader(Reader reader) {
        return reader;
    }
}
