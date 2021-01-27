package dental_clinic.core.services.doctor;

import dental_clinic.core.requests.doctor.GetDoctorListRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.GetDoctorListResponse;
import dental_clinic.core.validators.doctor.GetDoctorRequestListValidator;
import dental_clinic.core.database.doctor.DoctorDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetDoctorListService {

    @Autowired
    private DoctorDatabase doctorDatabase;
    @Autowired
    private GetDoctorRequestListValidator getDoctorRequestListValidator;

    public GetDoctorListResponse execute (GetDoctorListRequest getDoctorListRequest) {
        List<CoreError> errorList = getDoctorRequestListValidator.validate(getDoctorListRequest);

        if (!errorList.isEmpty()) {
            return new GetDoctorListResponse(errorList, new ArrayList<>());
        }

        if (doctorDatabase.getDoctorList().isEmpty()) {
            errorList.add(new CoreError("database", "Database is empty"));
            return new GetDoctorListResponse(errorList, new ArrayList<>());
        }

        return new GetDoctorListResponse(doctorDatabase.getDoctorList());
    }

}
