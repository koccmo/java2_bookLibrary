package lesson_12.core.services.reader.validators;


import lesson_12.core.requests.reader.DeleteReaderRequest;
import lesson_12.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteReaderValidator {

    public List<CoreError> validate(DeleteReaderRequest request){

        List<CoreError> errors = new ArrayList<>();
        String readerFirstName = request.getReaderFirstName();
        String readerLastName = request.getReaderLastName();
        String readerPersonalCode = request.getReaderPersonalCode();

        if(readerFirstName == null || readerFirstName.isEmpty()){
            errors.add(new CoreError("readerFirstName", "reader first name should not be empty!"));
        }

        if(readerLastName == null || readerLastName.isEmpty()){
            errors.add(new CoreError("readerLastName", "reader first name should not be empty!"));
        }

        if(readerPersonalCode == null || readerPersonalCode.isEmpty()){
            errors.add(new CoreError("readerPersonalCode", "reader first name should not be empty!"));
        }
        return errors;
    }
}
