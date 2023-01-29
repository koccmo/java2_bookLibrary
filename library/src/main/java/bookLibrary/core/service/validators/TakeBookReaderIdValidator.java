package bookLibrary.core.service.validators;

import bookLibrary.core.dataBase.ReaderBookRepository;
import bookLibrary.core.request.TakeBookRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TakeBookReaderIdValidator {
    @Autowired
    private ReaderBookRepository repository;

     public List<CoreError> validate(TakeBookRequest request) {
         List<CoreError> errors = new ArrayList<>();
         readerIdNotEmpty(request).ifPresent(errors::add);
         readerIdMustContainOnlyNumbers(request).ifPresent(errors::add);
         if(errors.isEmpty()) {
             hasReaderInDataBase(request).ifPresent(errors::add);
         }
         return errors;
     }

     private Optional<CoreError> hasReaderInDataBase(TakeBookRequest request) {
         return (repository.hasReaderInDataBase(Long.valueOf(request.getReaderId())))
                 ? Optional.empty()
                 : Optional.of(new CoreError("Reader", "Not found in DataBase"));
     }

     private Optional<CoreError> readerIdNotEmpty(TakeBookRequest request) {
         return (request.getReaderId().isEmpty() || request.getReaderId() == null)
                 ? Optional.of(new CoreError("ReaderId", "Must be fill up!"))
                 : Optional.empty();
     }

     private  Optional<CoreError> readerIdMustContainOnlyNumbers(TakeBookRequest request) {
         return (request.getReaderId().chars().allMatch(Character::isDigit))
                 ? Optional.empty()
                 : Optional.of(new CoreError("ReaderId", "Must contain only Numbers!"));
     }




}
