package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetRecordCompleteDateValidator {

    public List<CoreError> validate(SetRecordCompleteDateRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isUserIdEmpty(request)){
            errors.add(new CoreError("Record ID","must not be empty!"));
        }
        if (isUserIdNegative(request)){
            errors.add(new CoreError("Record ID","must not be negative!"));
        }

        return errors;
    }

    private boolean isUserIdEmpty(SetRecordCompleteDateRequest request) {
        return request.getRecordIdToSetCompleteDate() == null;
    }

    private boolean isUserIdNegative(SetRecordCompleteDateRequest request){
        return request.getRecordIdToSetCompleteDate() < 0;
    }
}
