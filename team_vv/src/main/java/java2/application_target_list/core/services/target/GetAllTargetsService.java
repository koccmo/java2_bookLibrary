package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class GetAllTargetsService {

    @Autowired private JpaTargetRepository jpaTargetRepository;

    public GetAllTargetsResponse execute(GetAllTargetsRequest request) {
        List<Target> targetList = jpaTargetRepository.findAll();
        return new GetAllTargetsResponse(targetList);
    }
}
