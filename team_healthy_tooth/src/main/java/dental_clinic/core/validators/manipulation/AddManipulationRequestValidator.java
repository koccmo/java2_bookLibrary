package dental_clinic.core.validators.manipulation;

import dental_clinic.core.requests.manipulation.AddManipulationRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddManipulationRequestValidator {

    public List<CoreError> validate (AddManipulationRequest addManipulationRequest) {
        List <CoreError> errorList = new ArrayList<>();

        if (addManipulationRequest.getManipulation().getManipulation_type() == null ||
            addManipulationRequest.getManipulation().getManipulation_type().isEmpty()) {
            errorList.add(new CoreError("manipulation type", "Not valid manipulation type"));
        }

        if (addManipulationRequest.getManipulation().getPrice() == null ||
        addManipulationRequest.getManipulation().getPrice() < 0) {
            errorList.add(new CoreError("price", "Not valid price"));
        }

        return errorList;
    }
}
