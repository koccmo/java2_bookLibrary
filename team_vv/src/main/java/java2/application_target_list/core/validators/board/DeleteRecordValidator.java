package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteRecordValidator {

    public List<CoreError> validate(DeleteRecordRequest request, BoardRepository boardRepository) {
        List<CoreError> errors = new ArrayList<>();


        if (!boardRepository.isIdInBoardList(request.getRecordIdToDelete())){
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


    private boolean isUserIdEmpty(DeleteRecordRequest request) {
        return request.getRecordIdToDelete() == null;
    }

    private boolean isUserIdNegative(DeleteRecordRequest request){
        return request.getRecordIdToDelete() < 0;
    }
}
