package dental_clinic.core.services.doctor;

import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.FillDoctorsWorkGraphicResponse;
import dental_clinic.core.validators.doctor.FillDoctorsWorkGraphicRequestValidator;
import dental_clinic.database.in_memory.doctor.DoctorDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FillDoctorsWorkGraphicService {

    @Autowired
    private FillDoctorsWorkGraphicRequestValidator fillDoctorsWorkGraphicRequestValidator;
    @Autowired
    private DoctorDatabase doctorDatabase;

    public FillDoctorsWorkGraphicResponse execute (FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest) {

        List <CoreError> errorList = fillDoctorsWorkGraphicRequestValidator.validate(fillDoctorsWorkGraphicRequest);

        if (!errorList.isEmpty()) {
            return new FillDoctorsWorkGraphicResponse(errorList);
        }

        if (!doctorDatabase.containsId(fillDoctorsWorkGraphicRequest.getId())) {
            errorList.add(new CoreError("database", "Database doesn't contain id " +
                    fillDoctorsWorkGraphicRequest.getId()));
            return new FillDoctorsWorkGraphicResponse(errorList);
        }

        return new FillDoctorsWorkGraphicResponse(fillDoctorsWorkGraphicRequest.getId(),
                fillDoctorsWorkGraphicRequest.getWorkGraphic());
    }

}
