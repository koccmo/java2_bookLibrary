package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.database.BoardDatabase;

import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetRecordCompleteDateValidator {

    public List<CoreError> validate(SetRecordCompleteDateRequest request, BoardDatabase boardDatabase) {
        List<CoreError> errors = new ArrayList<>();


        if (!boardDatabase.isIdInBoardList(request.getRecordIdToSetCompleteDate())){
            errors.add(new CoreError("Record ID","no record with that ID"));
        }

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
