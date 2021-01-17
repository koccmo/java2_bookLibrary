package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.board.BoardDatabase;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.validators.board.SetRecordCompleteDateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetRecordCompleteDateService {

    @Autowired private BoardDatabase boardDatabase;
    @Autowired private SetRecordCompleteDateValidator setRecordCompleteDateValidator;

    public SetRecordCompleteDateResponse execute(SetRecordCompleteDateRequest setRecordCompleteDateRequest){
        List<CoreError> errors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest, boardDatabase);

        if (!errors.isEmpty()){
            return new SetRecordCompleteDateResponse(errors);
        }

        boardDatabase.setRecordCompleteDate(setRecordCompleteDateRequest.getRecordIdToSetCompleteDate());
        return new SetRecordCompleteDateResponse(setRecordCompleteDateRequest.getRecordIdToSetCompleteDate());
    }
}
