package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetAllTargetsService {

    @Autowired
    private JpaTargetRepository jpaTargetRepository;

    private List<Target> targetList;

    public GetAllTargetsResponse execute(GetAllTargetsRequest request) {
        targetList = getAllTargetFromDB();
        return createGetAllTargetsResponse();
    }

    private GetAllTargetsResponse createGetAllTargetsResponse(){
        return new GetAllTargetsResponse(targetList);
    }

    private List<Target> getAllTargetFromDB(){
        return jpaTargetRepository.findAll();
    }
}
