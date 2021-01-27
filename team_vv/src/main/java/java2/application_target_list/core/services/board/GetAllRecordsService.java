package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllRecordsService {

    @Autowired private BoardRepository boardRepository;

    public GetAllRecordsResponse execute(GetAllRecordsRequest getAllRecordsRequest){
        return new GetAllRecordsResponse(boardRepository.getAllRecordsList());
    }
}
