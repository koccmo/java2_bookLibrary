package dental_clinic.core.services.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.DoctorAndGraphic;
import dental_clinic.core.requests.doctor.GetDoctorListRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.GetDoctorListResponse;
import dental_clinic.core.validators.doctor.GetDoctorRequestListValidator;
import dental_clinic.core.database.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetDoctorListService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private GetDoctorRequestListValidator getDoctorRequestListValidator;

    public GetDoctorListResponse execute (GetDoctorListRequest getDoctorListRequest) {
        List<CoreError> errorList = getDoctorRequestListValidator.validate(getDoctorListRequest);

        if (!errorList.isEmpty()) {
            return new GetDoctorListResponse(errorList, new ArrayList<>());
        }

        if (doctorRepository.getDoctorList().isEmpty()) {
            errorList.add(new CoreError("database", "Database is empty"));
            return new GetDoctorListResponse(errorList, new ArrayList<>());
        }

        List <DoctorAndGraphic> doctorAndGraphicList = new ArrayList<>();
        for (Doctor doctor : doctorRepository.getDoctorList()) {
            DoctorAndGraphic doctorAndGraphic = new DoctorAndGraphic(doctor, doctorRepository.getWorkGraphic(doctor));
            doctorAndGraphicList.add(doctorAndGraphic);
        }
        return new GetDoctorListResponse(doctorAndGraphicList);
    }

}
