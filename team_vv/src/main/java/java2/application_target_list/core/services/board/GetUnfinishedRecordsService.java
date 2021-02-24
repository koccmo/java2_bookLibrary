package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.requests.board.GetUnfinishedRecordsRequest;
import java2.application_target_list.core.responses.board.GetUnfinishedRecordsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//@Component
@Service
@Transactional
public class GetUnfinishedRecordsService {

    @Autowired private JpaBoardRepository jpaBoardRepository;

    public GetUnfinishedRecordsResponse execute(GetUnfinishedRecordsRequest getUnfinishedRecordsRequest){
        return new GetUnfinishedRecordsResponse(jpaBoardRepository.findUnfinishedRecords());
    }
}
