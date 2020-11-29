package dental_clinic.core.services;

import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ContainsDatabaseIdValidator {

    public List<CoreError> validate (ContainsDatabaseIdRequest request) {
        List<CoreError> errorsResult = new ArrayList<>();

        Long id = request.getId();

        if ((id == null) || (id < 1)) {

            errorsResult.add(new CoreError("id = ",
                    "Invalid input patient ID!"));
        }
        return errorsResult;
    }


}
