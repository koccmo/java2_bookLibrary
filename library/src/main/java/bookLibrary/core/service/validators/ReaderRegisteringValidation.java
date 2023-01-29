package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.ReaderRepository;
import bookLibrary.core.request.ReaderRegisteringRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReaderRegisteringValidation {
    @Autowired
    private ReaderRepository readerRepository;

    public List<CoreError> validate(ReaderRegisteringRequest request, ReaderRepository readerRepository) {
        List<CoreError> errors = new ArrayList<>();
        nameValidator(request).ifPresent(errors::add);
        lastNameValidator(request).ifPresent(errors::add);
        personalCodeIsNumbersValidator(request).ifPresent(errors::add);
        personalCodeNotNullOrEmptyValidator(request).ifPresent(errors::add);
        readerAlreadyRegistered(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> nameValidator(ReaderRegisteringRequest request) {
        return (request.getName() == null || request.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "Can`t be Empty or Null!"))
                : Optional.empty();
    }

    private Optional<CoreError> lastNameValidator(ReaderRegisteringRequest request) {
        return (request.getLastName() == null || request.getLastName().isEmpty())
                ? Optional.of(new CoreError("LastName", "Can`t be Empty or Null!"))
                : Optional.empty();
    }

    private Optional<CoreError> personalCodeNotNullOrEmptyValidator(ReaderRegisteringRequest request) {
        return request.getPersonalCode() == null || request.getPersonalCode().isEmpty()
                ? Optional.of(new CoreError("Personal Code", "Must be Filled Up!"))
                : Optional.empty();
    }

    private Optional<CoreError> personalCodeIsNumbersValidator(ReaderRegisteringRequest request) {
        return request.getPersonalCode().chars().allMatch(Character::isDigit)
                ? Optional.empty()
                : Optional.of(new CoreError("Personal Code", "Must Contain Only Numbers!"));
    }

    private Optional<CoreError> readerAlreadyRegistered(ReaderRegisteringRequest request) {
        return readerRepository.checkByIndexReaderAlreadyRegistered(request.getName(), request.getLastName(),
                Long.parseLong(request.getPersonalCode()))
                ? Optional.of(new CoreError("Reader", "It already Registered!"))
                : Optional.empty();
    }


}
