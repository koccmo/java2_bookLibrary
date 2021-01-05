package dental_clinic.core.services.visit;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.visit.AddVisitResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.visit.AddVisitValidator;
import dental_clinic.database.in_memory.doctor.DoctorDatabase;
import dental_clinic.database.in_memory.patient.PatientDatabase;
import dental_clinic.core.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.List;

@Component
public class AddVisitService {

    @Autowired
    private PatientDatabase patientDatabase;
    @Autowired
    private AddVisitValidator addVisitValidator;
    @Autowired
    private DoctorDatabase doctorDatabase;

    public AddVisitResponse execute(AddVisitRequest addVisitRequest){

        List<CoreError> errors = addVisitValidator.validate(addVisitRequest);

        if (!errors.isEmpty()){
            return new AddVisitResponse(errors);
        }

        if (notValidInputForDoctor(addVisitRequest.getDoctor())){
            errors.add(new CoreError("doctor", "Database doesn't contains specific doctor"));
            return new AddVisitResponse(errors);
        }

        if (!doctorIsEmployed(addVisitRequest.getDoctor())) {
            errors.add(new CoreError("doctor", "Doctor must be employed"));
            return new AddVisitResponse(errors);
        }

        Visit visit = new Visit(addVisitRequest.getToothNumber(), addVisitRequest.getComment(),
                addVisitRequest.getToothStatus(), addVisitRequest.getDoctor(), addVisitRequest.getDate());

        if (patientDatabase.containsPatientWithSpecificId(addVisitRequest.getPatientsId())){
            for (int i = 0; i < patientDatabase.getPatients().size(); i++) {
                if (isSpecificPatient(i, addVisitRequest.getPatientsId())) {
                    patientDatabase.getPatients().get(i).addVisit(visit);
                    patientDatabase.getPatients().get(i).updateJowl(addVisitRequest.getToothNumber(), addVisitRequest.getToothStatus());
                    return new AddVisitResponse();
                }
            }
        }

        errors.add(new CoreError("id", "Database doesn't contain patient with id " + addVisitRequest.getPatientsId()));
        return new AddVisitResponse(errors);

    }

    private boolean isSpecificPatient (int index, long id) {
        return patientDatabase.getPatients().get(index).getPersonalData().getId().equals(id);
    }

    private boolean notValidInputForDoctor(Doctor doctor) {
        return !doctorDatabase.containsDoctor(doctor);
    }

    private boolean doctorIsEmployed(Doctor doctor) {
        return doctorDatabase.specificDoctorIsEmployed(doctor);
    }

}
