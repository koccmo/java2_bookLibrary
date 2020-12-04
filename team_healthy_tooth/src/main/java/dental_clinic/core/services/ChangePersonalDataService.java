package dental_clinic.core.services;

import dental_clinic.core.requests.ChangePersonalDataRequest;
import dental_clinic.core.responses.ChangePersonalDataResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.validators.ChangePersonalDataValidator;
import dental_clinic.database.PatientDatabase;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class ChangePersonalDataService {

    @DIDependency private PatientDatabase patientDatabase;
    @DIDependency private ChangePersonalDataValidator validator;

    public ChangePersonalDataService(PatientDatabase patientDatabase,
                                     ChangePersonalDataValidator validator) {
        this.patientDatabase = patientDatabase;
        this.validator = validator;
    }

    public ChangePersonalDataResponse execute (ChangePersonalDataRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new ChangePersonalDataResponse(errors);
        }

        Long patientId = request.getPatientIdNumber();
        String updatedSurname = request.getUpdatedSurname();
        String updatedPhone = request.getUpdatedPhoneNumber();

        if (updatedSurname != null && !updatedSurname.isEmpty()){
            patientDatabase.changeSurname(patientId, updatedSurname);
        }

        if (updatedPhone != null && !updatedPhone.isEmpty()){
            patientDatabase.changePhone(patientId, updatedPhone);
        }

        return new ChangePersonalDataResponse(patientId);
    }

}