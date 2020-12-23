package java2.application_target_list.core.services;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.requests.GetAllTargetsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.GetAllTargetsResponse;


import java.util.List;

@Component
public class GetAllTargetsService {

    @Autowired
    private Database database;

    public GetAllTargetsResponse execute(GetAllTargetsRequest request) {
        List<Target> targetList = database.getTargetsList();
        return new GetAllTargetsResponse(targetList);
    }
}
