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

import java.util.List;
import java.util.Optional;

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

        Doctor doctor;

        if (isIdAdded(addVisitRequest.getDoctor().getName())) {
            Optional<Doctor> doctorOptional = doctorDatabase.getDoctorById(Long.parseLong(addVisitRequest.getDoctor().getName()));
            if (!doctorOptional.isPresent()) {
                errors.add(new CoreError("id", "Database doesn't contain doctor with id "
                        + addVisitRequest.getDoctor().getName() + " in database"));
                return new AddVisitResponse(errors);
            }
            doctor = doctorOptional.get();
        } else {
            doctor = new Doctor(addVisitRequest.getDoctor().getName().split(" ")[0],
                    addVisitRequest.getDoctor().getName().split(" ")[1]);
            doctor.setEmployed(true);
        }

        if (!doctor.getIsEmployed()) {
            errors.add(new CoreError("doctor", "Doctor must be employed"));
            return new AddVisitResponse(errors);
        }

        Visit visit = new Visit(addVisitRequest.getPatientsId(), addVisitRequest.getToothNumber(), addVisitRequest.getComment(),
                addVisitRequest.getToothStatus(), doctor, addVisitRequest.getDate());

        if (isNewDoctor(doctor)){
            doctorDatabase.addDoctor(doctor);
        }

        if (patientDatabase.containsPatientWithSpecificId(addVisitRequest.getPatientsId())){
            addVisitToDoctor(doctor, visit);
            return addVisitToPatient(addVisitRequest, visit);
        }

        errors.add(new CoreError("id", "Database doesn't contain patient with id " + addVisitRequest.getPatientsId()));
        return new AddVisitResponse(errors);

    }

    private boolean isIdAdded(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void addVisitToDoctor (Doctor doctor, Visit visit) {
        for (Doctor d : doctorDatabase.getDoctorList()) {
            if (d.getName().equals(doctor.getName())
            && d.getSurname().equals(doctor.getSurname())) {
                d.addVisit(visit);
            }
        }
    }

    private AddVisitResponse addVisitToPatient (AddVisitRequest addVisitRequest, Visit visit) {
        for (int i = 0; i < patientDatabase.getPatients().size(); i++) {
            if (isSpecificPatient(i, addVisitRequest.getPatientsId())) {
                patientDatabase.getPatients().get(i).addVisit(visit);
                patientDatabase.getPatients().get(i).updateJowl(addVisitRequest.getToothNumber(), addVisitRequest.getToothStatus());
                return new AddVisitResponse();
            }
        }
        return new AddVisitResponse();
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientDatabase.getPatients().get(index).getPersonalData().getId().equals(id);
    }

    private boolean isNewDoctor(Doctor doctor) {
        return !doctorDatabase.containsDoctor(doctor);
    }

}
