package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddRecordValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(AddRecordRequest addRecordRequest) {
        errors = new ArrayList<>();
        checkTargetId(addRecordRequest);
        checkUserId(addRecordRequest);
        return errors;
    }

    private void checkUserId(AddRecordRequest addRecordRequest){
        if (isUserIdEmpty(addRecordRequest)){
            errors.add(createUserIdIsEmptyError());
        }

        if (isUserIdNegative(addRecordRequest)){
            errors.add(createUserIdIsNegativeError());
        }
    }

    private void checkTargetId(AddRecordRequest addRecordRequest){
        if (isTargetIdEmpty(addRecordRequest)){
            errors.add(createTargetIdIsEmptyError());
        }

        if (isTargetIdNegative(addRecordRequest)){
            errors.add(createTargetIdIsNegativeError());
        }
    }

    private CoreError createUserIdIsNegativeError(){
        return new CoreError("User ID","must not be negative!");
    }

    private CoreError createUserIdIsEmptyError(){
        return new CoreError("User ID", "must not be empty!");
    }

    private CoreError createTargetIdIsNegativeError(){
        return new CoreError("Target ID","must not be negative!");
    }

    private CoreError createTargetIdIsEmptyError(){
        return new CoreError("Target ID","must not be empty!");
    }

    private boolean isTargetIdEmpty(AddRecordRequest request) {
        return request.getTargetId() == null;
    }

    private boolean isUserIdEmpty(AddRecordRequest request) {
        return request.getUserId() == null;
    }

    private boolean isTargetIdNegative(AddRecordRequest request){
        return request.getTargetId() < 0;
    }

    private boolean isUserIdNegative(AddRecordRequest request){
        return request.getUserId() < 0;
    }

}
