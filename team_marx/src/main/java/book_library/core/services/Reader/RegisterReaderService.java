package book_library.core.services.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.Reader.RegisterReaderResponse;
import book_library.core.validators.Reader.RegisterReaderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RegisterReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private RegisterReaderValidator validator;

    @Transactional
    public RegisterReaderResponse execute(RegisterReaderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new RegisterReaderResponse(errors);
        }

        Reader reader = new Reader(request.getReaderFirstName(), request.getReaderLastName(), request.getPersonalCode());
        readerRepository.save(reader);
        return new RegisterReaderResponse(reader);
    }

}
