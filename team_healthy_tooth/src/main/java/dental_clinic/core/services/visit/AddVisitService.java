package dental_clinic.core.services.visit;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.Manipulation;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.visit.AddVisitResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.visit.AddVisitValidator;
import dental_clinic.core.database.doctor.DoctorRepository;
import dental_clinic.core.database.manipulation.ManipulationRepository;
import dental_clinic.core.database.patient.PatientRepository;
import dental_clinic.core.domain.Visit;
import dental_clinic.core.database.visit.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AddVisitService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AddVisitValidator addVisitValidator;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ManipulationRepository manipulationRepository;
    @Autowired
    private VisitRepository visitRepository;

    public AddVisitResponse execute(AddVisitRequest addVisitRequest){

        List<CoreError> errors = addVisitValidator.validate(addVisitRequest);

        if (!errors.isEmpty()){
            return new AddVisitResponse(errors);
        }

        Doctor doctor;
        if (doctorRepository.containsId(addVisitRequest.getDoctorId())) {
            doctor = doctorRepository.getDoctorById(addVisitRequest.getDoctorId()).get();
        } else {
            errors.add(new CoreError("doctor", "Database doesn't contain doctor with id "
                    + addVisitRequest.getDoctorId()));
            return new AddVisitResponse(errors);
        }


        if (!doctor.isEmployed()) {
            errors.add(new CoreError("doctor", "Doctor must be employed"));
            return new AddVisitResponse(errors);
        }

        errors.addAll(manipulationsDatabaseContainsIdAndIsActive(addVisitRequest.getManipulationId()));
        if (!errors.isEmpty()){
            return new AddVisitResponse(errors);
        }

        Manipulation manipulation = manipulationRepository.getManipulationById(addVisitRequest.getManipulationId());

        PersonalData personalData = patientRepository.getPersonalDataById(addVisitRequest.getPatientId());

        Visit visit = new Visit(personalData, addVisitRequest.getToothNumber(), addVisitRequest.getComment(),
                addVisitRequest.getToothStatus(), doctor,
                manipulation, new Date(), manipulation.getPrice());

        if (isNewDoctor(doctor)){
            doctorRepository.addDoctor(doctor);
        }

        visitRepository.addVisit(visit);

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

    private boolean isNewDoctor(Doctor doctor) {
        return !doctorRepository.containsDoctor(doctor);
    }

    private List<CoreError> manipulationsDatabaseContainsIdAndIsActive(Long manipulationId) {
        List<CoreError>errors = new ArrayList<>();
            if (!manipulationRepository.manipulationIsActive(manipulationId)) {
                errors.add(new CoreError("manipulation", "Manipulation with id " +
                        manipulationId + " isn't active"));
            }
        return errors;
    }
}
