package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.board.BoardDatabase;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.validators.board.AddRecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddRecordService {

    @Autowired private BoardDatabase boardDatabase;
    @Autowired private AddRecordValidator addRecordValidator;

    public AddRecordResponse execute(AddRecordRequest addRecordRequest){
        List<CoreError> errors = addRecordValidator.validate(addRecordRequest);

        if (!errors.isEmpty()){
            return new AddRecordResponse(errors);
        }

        Record record = new Record(addRecordRequest.getTargetId(), addRecordRequest.getUserId());
        boardDatabase.addToBoard(record);

        return new AddRecordResponse(record);
    }
}
