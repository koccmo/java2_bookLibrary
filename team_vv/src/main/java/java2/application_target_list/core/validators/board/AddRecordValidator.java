package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.database.target.TargetDatabase;
import java2.application_target_list.core.database.user.UserDatabase;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddRecordValidator {

    @Autowired TargetDatabase targetDatabase;
    @Autowired UserDatabase userDatabase;

    public List<CoreError> validate(AddRecordRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","must not be empty!"));
        }

        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","must not be negative!"));
        }

        if (isUserIdEmpty(request)){
            errors.add(new CoreError("User ID", "must not be empty!"));
        }

        if (isUserIdNegative(request)){
            errors.add(new CoreError("User ID","must not be negative!"));
        }

        if (!isTargetIdIDB(request)){
            errors.add(new CoreError("Target ID","no target with that ID!"));
        }

        if (!isUserIdIDB(request)){
            errors.add(new CoreError("User ID","no user with that ID!"));
        }

        return errors;
    }

    private boolean isTargetIdIDB(AddRecordRequest request){
        return targetDatabase.isIdInTargetList(request.getTargetId());
    }

    private boolean isUserIdIDB(AddRecordRequest request){
        return userDatabase.isIdInUserList(request.getUserId());
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
