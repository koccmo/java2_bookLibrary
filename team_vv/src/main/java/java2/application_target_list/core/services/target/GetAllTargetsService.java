package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.TargetDatabase;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;


import java.util.List;

@Component
public class GetAllTargetsService {

    @Autowired
    private TargetDatabase targetDatabase;

    public GetAllTargetsResponse execute(GetAllTargetsRequest request) {
        List<Target> targetList = targetDatabase.getTargetsList();
        return new GetAllTargetsResponse(targetList);
    }
}
