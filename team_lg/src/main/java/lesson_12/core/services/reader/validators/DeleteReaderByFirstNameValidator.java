package lesson_12.core.services.reader.validators;

import lesson_12.core.requests.reader.DeleteReaderByFirstNameRequest;
import lesson_12.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteReaderByFirstNameValidator {
    public List<CoreError> validate(DeleteReaderByFirstNameRequest request){

        List<CoreError> errors = new ArrayList<>();
        String readerFirstName = request.getReaderFirstName();

        if(readerFirstName == null || readerFirstName.isEmpty()){
            errors.add(new CoreError("readerFirstName", "reader first name should not be empty!"));
        }
        return errors;
    }
}
