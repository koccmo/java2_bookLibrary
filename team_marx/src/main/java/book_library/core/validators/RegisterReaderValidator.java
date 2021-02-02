package book_library.core.validators;

import book_library.core.database.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.RegisterReaderRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.RegisterReaderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RegisterReaderValidator {

    @Autowired
    private ReaderRepository readerRepository;

    public List<CoreError> validate (RegisterReaderRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateFirstName(request).ifPresent(errors::add);
        validateLastName(request).ifPresent(errors::add);
        if(errors.isEmpty()){
            validatePresenceInDatabase(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateFirstName (RegisterReaderRequest request) {
        return (request.getReaderFirstName() == null || request.getReaderFirstName().isEmpty())
                ? Optional.of(new CoreError("firstName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLastName (RegisterReaderRequest request) {
        return (request.getReaderLastName() == null || request.getReaderLastName().isEmpty())
                ? Optional.of(new CoreError("lastName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePresenceInDatabase (RegisterReaderRequest request) {
        Reader reader= new Reader(request.getReaderFirstName(), request.getReaderLastName());
        return  readerRepository.hasTheSameReaderInDatabase(reader)
                ? Optional.of(new CoreError("First name and last name", "This reader already is registered"))
                : Optional.empty();
    }
}
