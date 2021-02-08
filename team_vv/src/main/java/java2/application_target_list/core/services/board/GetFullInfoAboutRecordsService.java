package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.requests.board.GetFullInfoAboutRecordsRequest;
import java2.application_target_list.core.responses.board.GetFullInfoAboutRecordsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetFullInfoAboutRecordsService {

    @Autowired
    BoardRepository boardRepository;

    public GetFullInfoAboutRecordsResponse execute(GetFullInfoAboutRecordsRequest getFullInfoAboutRecordsRequest){
        return new GetFullInfoAboutRecordsResponse(boardRepository.getFullInfoAboutRecords());
    }
}
