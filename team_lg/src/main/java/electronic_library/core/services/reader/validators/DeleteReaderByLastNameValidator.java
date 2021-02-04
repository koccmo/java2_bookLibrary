package electronic_library.core.services.reader.validators;

import electronic_library.core.requests.reader.DeleteReaderByLastNameRequest;
import electronic_library.core.responses.CoreError;
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
