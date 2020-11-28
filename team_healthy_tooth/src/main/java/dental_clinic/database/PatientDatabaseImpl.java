package dental_clinic.database;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.Visit;

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
    public void deletePatient(Long id) {
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPersonalData().getId() == id){
                patientList.remove(i);
            }
        }
    }

    @Override
    public Optional <Patient> getSpecificPatientHistory(Long id) {
            for (int i = 0; i < patientList.size(); i++){
                if (isSpecificPatient(i, id)){
                    return Optional.of(patientList.get(i));
                }
            }
            return Optional.empty();
    }

    @Override
    public List<Patient> findPatientByName(String name) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getName().toLowerCase().startsWith(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsBySurname(String surname) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getSurname().toLowerCase().startsWith(surname.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsByNameAndSurname(String name, String surname) {
        return patientList.stream()
                .filter(patient ->
                        patient.getPersonalData().getName().toLowerCase().startsWith(name.toLowerCase()) &&
                                patient.getPersonalData().getSurname().toLowerCase().startsWith(surname.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsByPersonalCode(String personalCode) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    @Override
    public void addVisit(Long id, Visit newVisit) {
        for (int i = 0; i < patientList.size(); i++){
            if (isSpecificPatient(i, id)){
                Visit visit =
                        new Visit(newVisit.getToothNumber(), newVisit.getComment(), newVisit.getToothStatus(), newVisit.getDoctor());
                patientList.get(i).addVisit(visit);
                patientList.get(i).updateJowl(newVisit.getToothNumber(), newVisit.getToothStatus());
            }
        }
    }

    @Override
    public Optional<Patient> getPatientCard(Long id) {
        return patientList.stream()
                .filter(patient -> patient.getPersonalData().getId() == id)
                .findAny();
    }

    private boolean isSpecificPatient (int index, Long id) {
        return patientList.get(index).getPersonalData().getId() == id;
    }

    @Override
    public boolean checkPatientById(Long id) {
        boolean statusId = false;
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPersonalData().getId() == id){
                statusId = true;

            }
        }
        return statusId;
    }

    @Override
    public void changeSurname(long idToSearch, String updatedSurname) {
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPersonalData().getId() == idToSearch){
                    patientList.get(i).getPersonalData().setSurname(updatedSurname);
            }
        }
    }

    @Override
    public void changePhone(long idToSearch, String updatedPhone) {
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPersonalData().getId() == idToSearch){
                patientList.get(i).getPersonalData().setPhone(updatedPhone);
            }
        }
    }

    @Override
    public Optional<Patient> findPatientByIdNumber(long idToSearch) {
        Optional<Patient> result = Optional.empty();
        for (Patient patient : patientList) {
            long foundId = patient.getPersonalData().getId();
            if (foundId == idToSearch) {
                result = Optional.of(patient);
            }
        }
        return result;
    }

}
