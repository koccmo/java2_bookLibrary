package dental_clinic.core.validators.doctor;

import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FillDoctorsWorkGraphicRequestValidator {

    public List<CoreError> validate (FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest) {
        List <CoreError> errorList = new ArrayList<>();


        return errorList;
    }
}
