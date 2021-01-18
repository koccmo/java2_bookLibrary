package dental_clinic.core.validators.manipulation;

import dental_clinic.core.requests.manipulation.GetManipulationsListRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetManipulationsRequestValidator {

    public List<CoreError> validate (GetManipulationsListRequest getManipulationsListRequest) {
        return new ArrayList<>();
    }
}
