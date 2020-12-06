package application_target_list.core.services;

import application_target_list.core.database.Target;
import application_target_list.core.database.Database;
import application_target_list.core.requests.GetAllTargetsRequest;
import application_target_list.core.responses.GetAllTargetsResponse;
import application_target_list.dependency_injection.DIComponent;
import application_target_list.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class GetAllTargetsService {

    @DIDependency private Database database;

    public GetAllTargetsResponse execute(GetAllTargetsRequest request) {
        List<Target> targetList = database.getTargetsList();
        return new GetAllTargetsResponse(targetList);
    }
}
