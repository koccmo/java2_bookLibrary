package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetRecordCompleteDateValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(SetRecordCompleteDateRequest setRecordCompleteDateRequest) {
        errors = new ArrayList<>();
        checkRecordId(setRecordCompleteDateRequest);
        return errors;
    }

    private void checkRecordId(SetRecordCompleteDateRequest setRecordCompleteDateRequest){
        if (isUserIdEmpty(setRecordCompleteDateRequest)){
            errors.add(createRecordIdIsEmptyError());
        }
        if (isUserIdNegative(setRecordCompleteDateRequest)){
            errors.add(createRecordIdISNegativeError());
        }
    }

    private CoreError createRecordIdISNegativeError(){
        return new CoreError("Record ID","must not be negative!");
    }

    private CoreError createRecordIdIsEmptyError(){
        return new CoreError("Record ID","must not be empty!");
    }

    private boolean isUserIdEmpty(SetRecordCompleteDateRequest request) {
        return request.getRecordIdToSetCompleteDate() == null;
    }

    private boolean isUserIdNegative(SetRecordCompleteDateRequest request){
        return request.getRecordIdToSetCompleteDate() < 0;
    }
}
