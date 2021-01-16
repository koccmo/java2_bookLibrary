package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.BoardDatabase;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.validators.board.DeleteRecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteRecordService {

    @Autowired private BoardDatabase boardDatabase;
    @Autowired private DeleteRecordValidator deleteRecordValidator;

    public DeleteRecordResponse execute(DeleteRecordRequest deleteRecordRequest){
        List<CoreError> errors = deleteRecordValidator.validate(deleteRecordRequest, boardDatabase);

        if (!errors.isEmpty()){
            return new DeleteRecordResponse(errors);
        }

        boardDatabase.deleteFromBoard(deleteRecordRequest.getRecordIdToDelete());
        return new DeleteRecordResponse(deleteRecordRequest.getRecordIdToDelete());
    }
}
