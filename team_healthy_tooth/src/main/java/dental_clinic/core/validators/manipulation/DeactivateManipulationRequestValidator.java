package dental_clinic.core.validators.manipulation;

import dental_clinic.core.requests.manipulation.DeactivateManipulationRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeactivateManipulationRequestValidator {

    public List<CoreError> validate (DeactivateManipulationRequest deactivateManipulationRequest) {

        List <CoreError> errorList = new ArrayList<>();

        if (deactivateManipulationRequest.getId() == null ||
        deactivateManipulationRequest.getId() < 1);

        return errorList;
    }

}
