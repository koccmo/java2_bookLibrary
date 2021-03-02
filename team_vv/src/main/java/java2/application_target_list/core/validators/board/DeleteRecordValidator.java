package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteRecordValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(DeleteRecordRequest deleteRecordRequest) {
        errors = new ArrayList<>();
        checkRecordId(deleteRecordRequest);
        return errors;
    }

    private void checkRecordId(DeleteRecordRequest deleteRecordRequest){
        if (isUserIdEmpty(deleteRecordRequest)){
            errors.add(createRecordIdIsEmptyError());
        }
        if (isUserIdNegative(deleteRecordRequest)){
            errors.add(createRecordIdIsNegativeError());
        }
    }

    private CoreError createRecordIdIsNegativeError(){
        return new CoreError("Record ID","must not be negative!");
    }

    private CoreError createRecordIdIsEmptyError(){
        return new CoreError("Record ID","must not be empty!");
    }

    private boolean isUserIdEmpty(DeleteRecordRequest request) {
        return request.getRecordIdToDelete() == null;
    }

    private boolean isUserIdNegative(DeleteRecordRequest request){
        return request.getRecordIdToDelete() < 0;
    }

}
