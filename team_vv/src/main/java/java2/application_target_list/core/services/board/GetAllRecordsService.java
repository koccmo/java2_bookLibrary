package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.BoardDatabase;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllRecordsService {

    @Autowired private BoardDatabase boardDatabase;

    public GetAllRecordsResponse execute(GetAllRecordsRequest getAllRecordsRequest){
        return new GetAllRecordsResponse(boardDatabase.getAllRecordsList());
    }
}
