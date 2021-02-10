package lesson_12.core.services.reader;

import lesson_12.core.database.reader.ReaderRepository;
import lesson_12.core.domain.Reader;
import lesson_12.core.requests.reader.AddReaderRequest;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.reader.AddReaderResponse;
import lesson_12.core.services.reader.validators.AddReaderValidator;
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
