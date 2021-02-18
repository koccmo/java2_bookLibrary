package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.requests.board.GetRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.GetRecordResponse;
import java2.application_target_list.core.validators.board.GetRecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRecordService {

    @Autowired private GetRecordValidator getRecordValidator;
    @Autowired private BoardRepository boardRepository;

    public GetRecordResponse execute(GetRecordRequest getRecordRequest){
        List<CoreError> errors = getRecordValidator.validate(getRecordRequest);

        if (!errors.isEmpty()) {
            return new GetRecordResponse(errors);
        }

        return boardRepository.getById(getRecordRequest.getId())
                .map(GetRecordResponse::new)
                .orElseGet(() -> {
                     errors.add(new CoreError("id", "Not found"));
                    return new GetRecordResponse(errors);});
    }

}
