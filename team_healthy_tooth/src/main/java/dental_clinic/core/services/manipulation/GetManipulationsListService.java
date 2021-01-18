package dental_clinic.core.services.manipulation;

import dental_clinic.core.domain.Manipulation;
import dental_clinic.core.requests.manipulation.GetManipulationsListRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.manipulation.GetManipulationsListResponse;
import dental_clinic.core.validators.manipulation.GetManipulationsRequestValidator;
import dental_clinic.database.in_memory.manipulation.ManipulationInMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetManipulationsListService {

    @Autowired
    private GetManipulationsRequestValidator getManipulationsRequestValidator;
    @Autowired
    private ManipulationInMemoryDatabase manipulationInMemoryDatabase;

    public GetManipulationsListResponse execute (GetManipulationsListRequest getManipulationsListRequest) {
        List<CoreError> errorList = getManipulationsRequestValidator.validate(getManipulationsListRequest);

        if (!errorList.isEmpty()) {
            return new GetManipulationsListResponse(errorList, new ArrayList<>());
        }

        List <Manipulation> manipulations = manipulationInMemoryDatabase.getManipulationsList();

        if (manipulations.isEmpty()) {
            errorList.add(new CoreError("database", "Database is empty"));
            return new GetManipulationsListResponse(errorList, new ArrayList<>());
        }

        return new GetManipulationsListResponse(manipulations);
    }
}