package dental_clinic.database;

import dental_clinic.domain.Patient;
import dental_clinic.domain.PersonalData;
import dental_clinic.domain.ToothStatus;

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
    public boolean addVisit(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus) {
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPersonalData().getId() == id){
                patientList.get(i).getJowl().updateJowl(toothNumber, comment, toothStatus);
                return true;
            }
        }
        return false;
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientList.get(index).getPersonalData().getId() == id;
    }

    //Тут я размышляла надо ли на карточке печатать последнее инфо или всю историю... :)
    private void printActualInfoAboutJowl(int index){
        /*Map<Integer, ToothInfo> result = patientList.get(index).getJowl();
        String toothInfoForPrint = "";

        for (Integer key : result.keySet()){
            toothInfoForPrint += key + " ";
            if (result.get(key).getComment().size() > 0){
                if (!result.get(key).getComment().get(result.get(key).getComment().size()-1).equals(Optional.empty())){
                    toothInfoForPrint += result.get(key).getComment().get(result.get(key).getComment().size()-1) + " ";
                }
            }
            toothInfoForPrint += result.get(key).getStatus().get(result.get(key).getStatus().size()-1) + "\n";
        }

        System.out.println(toothInfoForPrint);*/
    }
}
