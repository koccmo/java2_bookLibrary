package dental_clinic.core.services;

import dental_clinic.core.requests.AddVisitRequest;
import dental_clinic.core.responses.AddVisitResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.database.PatientDatabase;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddVisitService {

    private final PatientDatabase patientDatabase;
    private final AddVisitValidator addVisitValidator;

    public AddVisitService(PatientDatabase patientDatabase, AddVisitValidator addVisitValidator) {
        this.patientDatabase = patientDatabase;
        this.addVisitValidator = addVisitValidator;
    }

    public AddVisitResponse execute(AddVisitRequest addVisitRequest){

        List<CoreError> errors = addVisitValidator.validate(addVisitRequest);

        if (!errors.isEmpty()){
            return new AddVisitResponse(errors);
        }

        for (int i = 0; i < patientDatabase.getPatients().size(); i++){
            if (isSpecificPatient(i, addVisitRequest.getId())){
                Visit visit = new Visit(addVisitRequest.getToothNumber(), addVisitRequest.getComment(),
                        addVisitRequest.getToothStatus(), addVisitRequest.getDoctor());
                patientDatabase.getPatients().get(i).addVisit(visit);
                patientDatabase.getPatients().get(i).updateJowl(addVisitRequest.getToothNumber(), addVisitRequest.getToothStatus());
                return new AddVisitResponse();
            }
        }
        errors.add(new CoreError("id", "Database doesnt't contain patient wirh id " + addVisitRequest.getId()));
        return new AddVisitResponse(errors);
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientDatabase.getPatients().get(index).getPersonalData().getId() == id;
    }
}
