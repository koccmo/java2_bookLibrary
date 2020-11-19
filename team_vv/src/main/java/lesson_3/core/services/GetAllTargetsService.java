package lesson_3.core.services;

import lesson_3.Target;
import lesson_3.core.database.Database;
import lesson_3.core.requests.GetAllTargetsRequest;
import lesson_3.core.responses.GetAllTargetsResponse;

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
