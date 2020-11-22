package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.SearchPatientResponse;
import dental_clinic.database.PatientDatabase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPatientService {

    private final PatientDatabase patientDatabase;
    private final SearchPatientRequestValidator searchPatientRequestValidator;

    public SearchPatientService(PatientDatabase patientDatabase, SearchPatientRequestValidator searchPatientRequestValidator) {
        this.patientDatabase = patientDatabase;
        this.searchPatientRequestValidator = searchPatientRequestValidator;
    }

    public SearchPatientResponse execute (SearchPatientRequest searchPatientRequest){

        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        if (!errors.isEmpty()){
            return new SearchPatientResponse(errors, new ArrayList<>());
        }

        if (isNameAndSurnameFilled(searchPatientRequest.getName(), searchPatientRequest.getSurname())){
            return searchByNameAndSurnameIsProvided(searchPatientRequest);
        }

        if (isNameFilled(searchPatientRequest.getName())){
            return searchByNameIsProvided(searchPatientRequest);
        }

        return searchBySurnameIsProvided(searchPatientRequest);
    }

    private boolean isNameAndSurnameFilled(String name, String surname){
        return name != null && !name.isEmpty() &&
                surname != null && !surname.isEmpty();
    }

    private SearchPatientResponse searchByNameAndSurnameIsProvided(SearchPatientRequest searchPatientRequest){
        List<CoreError>errors = new ArrayList<>();
        List <Patient> patients = order(patientDatabase.findPatientsByNameAndSurname(searchPatientRequest.getName(),
                searchPatientRequest.getSurname()), searchPatientRequest.getOrdering());
        if (patients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with name " +
                    searchPatientRequest.getName() + " and surname " + searchPatientRequest.getSurname()));
            return new SearchPatientResponse(errors, new ArrayList<>());
        }
        return new SearchPatientResponse(patients);
    }

    private boolean isNameFilled(String name){
        return name != null && !name.isEmpty();
    }

    private SearchPatientResponse searchByNameIsProvided(SearchPatientRequest searchPatientRequest){
        List<CoreError>errors = new ArrayList<>();
        List <Patient> patients = order(patientDatabase.findPatientByName(searchPatientRequest.getName()),
                searchPatientRequest.getOrdering());
        if (patients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with name " +
                    searchPatientRequest.getName()));
            return new SearchPatientResponse(errors, new ArrayList<>());
        }
        return new SearchPatientResponse(patients);
    }

    private SearchPatientResponse searchBySurnameIsProvided(SearchPatientRequest searchPatientRequest){
        List<CoreError>errors = new ArrayList<>();
        List<Patient>patients = order(patientDatabase.findPatientsBySurname(searchPatientRequest.getSurname()),
                searchPatientRequest.getOrdering());
        if (patients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with surname " +
                    searchPatientRequest.getSurname()));
            return new SearchPatientResponse(errors, new ArrayList<>());
        }

        return new SearchPatientResponse(patients);
    }

    private List<Patient> order(List<Patient> patients, Ordering ordering) {
        if (ordering.filledBoth()){
            if (ordering.getOrderBy().equals("name")){
                if (ordering.getOrderDirection().equals("ASC")){
                    return patients.stream()
                            .sorted(Comparator.comparing(patient -> patient.getPersonalData().getName()))
                            .collect(Collectors.toList());

                }else{
                    return patients.stream()
                            .sorted((o1,o2) -> o2.getPersonalData().getName().compareTo(o1.getPersonalData().getName()))
                            .collect(Collectors.toList());
                }
            }else{
                if (ordering.getOrderDirection().equals("ASC")){
                    return patients.stream()
                            .sorted(Comparator.comparing(patient -> patient.getPersonalData().getSurname()))
                            .collect(Collectors.toList());

                }else{
                    return patients.stream()
                            .sorted((o1,o2) -> o2.getPersonalData().getSurname().compareTo(o1.getPersonalData().getSurname()))
                            .collect(Collectors.toList());                }
            }
        }else{
            return patients;
        }
    }
}
