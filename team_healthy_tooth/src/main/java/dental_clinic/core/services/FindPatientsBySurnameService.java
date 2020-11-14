package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.FindPatientBySurnameRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.FindPatientBySurnameResponse;
import dental_clinic.database.PatientDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindPatientsBySurnameService {

    private final PatientDatabase patientDatabase;
    private final FindPatientsBySurnameValidator findPatientsBySurnameValidator;

    public FindPatientsBySurnameService(PatientDatabase patientDatabase,
                                        FindPatientsBySurnameValidator findPatientsBySurnameValidator) {
        this.patientDatabase = patientDatabase;
        this.findPatientsBySurnameValidator = findPatientsBySurnameValidator;
    }

    public FindPatientBySurnameResponse execute(FindPatientBySurnameRequest findPatientBySurnameRequest){

        List <CoreError> errors = findPatientsBySurnameValidator.validate(findPatientBySurnameRequest);

        if (!errors.isEmpty()){
            return new FindPatientBySurnameResponse(errors, new ArrayList<>());
        }

        List <Patient> specificPatients = patientDatabase.getPatients().stream()
                .filter(patient -> getSpecificPatientSurname(patient).startsWith(findPatientBySurnameRequest.getSurname().toLowerCase()))
                .collect(Collectors.toList());

        if (specificPatients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with surname "
                    + findPatientBySurnameRequest.getSurname()));
            return new FindPatientBySurnameResponse(errors, new ArrayList<>());
        }

        return new FindPatientBySurnameResponse(specificPatients);

    }

    private String getSpecificPatientSurname(Patient patient){
        return patient.getPersonalData().getSurname().toLowerCase();
    }
}
