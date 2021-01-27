package dental_clinic.core.services.manipulation;

import dental_clinic.core.requests.manipulation.AddManipulationRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.manipulation.AddManipulationResponse;
import dental_clinic.core.validators.manipulation.AddManipulationRequestValidator;
import dental_clinic.core.database.manipulation.ManipulationDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddManipulationService {

    @Autowired
    private AddManipulationRequestValidator addManipulationRequestValidator;
    @Autowired
    private ManipulationDatabase manipulationDatabase;

    public AddManipulationResponse execute (AddManipulationRequest addManipulationRequest) {
        List<CoreError> errorList = addManipulationRequestValidator.validate(addManipulationRequest);

        if (!errorList.isEmpty()) {
            return new AddManipulationResponse(errorList);
        }

        if (manipulationDatabase.containsTheSameManipulation(addManipulationRequest.getManipulation())) {
            errorList.add(new CoreError("database", "Database contains the same manipulation"));
            return new AddManipulationResponse(errorList);
        }

        manipulationDatabase.addManipulation(addManipulationRequest.getManipulation());
        return new AddManipulationResponse(addManipulationRequest.getManipulation());
    }
}
