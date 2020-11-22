package application_target_list.core.services;

import application_target_list.core.database.Target;
import application_target_list.core.database.Database;
import application_target_list.core.requests.GetAllTargetsRequest;
import application_target_list.core.responses.GetAllTargetsResponse;

import java.util.List;


public class GetAllTargetsService {

    private Database database;

    public GetAllTargetsService(Database database) {
        this.database = database;
    }

    public GetAllTargetsResponse execute(GetAllTargetsRequest request) {
        List<Target> targetList = database.getTargetsList();
        return new GetAllTargetsResponse(targetList);
    }
}
