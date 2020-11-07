package dental_clinic.database;

import dental_clinic.domain.Patient;
import dental_clinic.domain.PersonalData;
import dental_clinic.domain.ToothStatus;
import dental_clinic.domain.Visit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientDatabaseImpl implements PatientDatabase {

    private Long id= 1L;
    private List<Patient> patientList = new ArrayList<>();

    @Override
    public List<Patient> getPatients(){
        return patientList;
    }

    @Override
    public void addPatient(PersonalData personalData) {
            personalData.setId(id);
            patientList.add(new Patient(personalData));
            id++;
    }

    @Override
    public void deletePatient(long id) {
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPersonalData().getId() == id){
                patientList.remove(i);
            }
        }
    }

    @Override
    public Optional <Patient> getSpecificPatientHistory(long id) {
            for (int i = 0; i < patientList.size(); i++){
                if (isSpecificPatient(i, id)){
                    return Optional.of(patientList.get(i));
                }
            }
            return Optional.empty();
    }

    @Override
    public List<Patient> findPatientBySurname(String surname) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getSurname().equalsIgnoreCase(surname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientByPersonalCode(String personalCode) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    @Override
    public void addVisit(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus, String doctor) {
        for (int i = 0; i < patientList.size(); i++){
            if (isSpecificPatient(i, id)){
                Visit visit = new Visit(toothNumber, comment, toothStatus, doctor);
                patientList.get(i).addVisit(visit);
                patientList.get(i).updateJowl(toothNumber, toothStatus);
            }
        }
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientList.get(index).getPersonalData().getId() == id;
    }

}
