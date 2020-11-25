package internet_store.core.services;

import internet_store.Target;
import internet_store.core.database.Database;
import internet_store.core.requests.GetAllTargetsRequest;
import internet_store.core.responses.GetAllTargetsResponse;

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
