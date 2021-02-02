package book_library.core.services;

import book_library.core.database.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.RegisterReaderRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.RegisterReaderResponse;
import book_library.core.validators.RegisterReaderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private RegisterReaderValidator validator;

    public RegisterReaderResponse execute(RegisterReaderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new RegisterReaderResponse(errors);
        }

        Reader reader = new Reader(request.getReaderFirstName(), request.getReaderLastName());
        readerRepository.save(reader);
        return new RegisterReaderResponse(reader);
    }

}
