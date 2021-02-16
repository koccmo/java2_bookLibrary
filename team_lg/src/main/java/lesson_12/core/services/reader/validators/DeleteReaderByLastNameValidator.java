package lesson_12.core.services.reader.validators;

import lesson_12.core.requests.reader.DeleteReaderByLastNameRequest;
import lesson_12.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteReaderByLastNameValidator {
    public List<CoreError> validate(DeleteReaderByLastNameRequest request){

        List<CoreError> errors = new ArrayList<>();
        String readerLastName = request.getReaderLastName();

        if(readerLastName == null || readerLastName.isEmpty()){
            errors.add(new CoreError("readerLastName", "reader first name should not be empty!"));
        }
        return errors;
    }
}
