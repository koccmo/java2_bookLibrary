package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.requests.board.GetUnfinishedRecordsRequest;
import java2.application_target_list.core.responses.board.GetUnfinishedRecordsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUnfinishedRecordsService {

    @Autowired
    private BoardRepository boardRepository;

    public GetUnfinishedRecordsResponse execute(GetUnfinishedRecordsRequest getUnfinishedRecordsRequest){
        return new GetUnfinishedRecordsResponse(boardRepository.getUnfinishedRecords());
    }
}
