package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetAllRecordsService {

    @Autowired
    private JpaBoardRepository jpaBoardRepository;

    private List<Record> records;

    public GetAllRecordsResponse execute(GetAllRecordsRequest getAllRecordsRequest){
        records = getAllRecordsFromDB();
        return createGetAllRecordsResponse();
    }

    private List<Record> getAllRecordsFromDB(){
        return jpaBoardRepository.findAll();
    }

    private GetAllRecordsResponse createGetAllRecordsResponse() {
        return new GetAllRecordsResponse(records);
    }
}
