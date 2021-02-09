package book_library.core.validators.Reader;

import book_library.core.database.Reader.ReaderRepository;
import book_library.core.domain.Reader;
import book_library.core.requests.Reader.RegisterReaderRequest;
import book_library.core.responses.CoreError;
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
        validatePersonalCode(request).ifPresent(errors::add);
        if(errors.isEmpty()){
            validatePersonalCodeLength(request).ifPresent(errors::add);
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

    private Optional<CoreError> validatePersonalCode (RegisterReaderRequest request) {
        return (request.getPersonalCode() == null)
                ? Optional.of(new CoreError("personalCode", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePersonalCodeLength (RegisterReaderRequest request) {
        return (request.getPersonalCode().toString().length() != 11)
                ? Optional.of(new CoreError("personalCode", "Must contain 11 digits!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePresenceInDatabase (RegisterReaderRequest request) {
        Reader reader= new Reader(request.getReaderFirstName(), request.getReaderLastName(), request.getPersonalCode());
        return  readerRepository.hasTheSameReaderInDatabase(reader)
                ? Optional.of(new CoreError("First name, last name and personal code", "This reader already is registered"))
                : Optional.empty();
    }
}
