package dental_clinic.core.validators.user;

import dental_clinic.core.requests.user.GetUsersRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class GetUsersRequestValidator {

    public List<CoreError> validate (GetUsersRequest getUsersRequest) {
        return new ArrayList<>();
    }
}
