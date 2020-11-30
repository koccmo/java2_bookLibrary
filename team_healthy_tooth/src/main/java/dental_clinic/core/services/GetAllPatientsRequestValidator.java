package dental_clinic.core.services;

import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class GetAllPatientsRequestValidator {

    public List<CoreError> validate (GetAllPatientsRequest getAllPatientsRequest){

        return new ArrayList<>();
    }
}
