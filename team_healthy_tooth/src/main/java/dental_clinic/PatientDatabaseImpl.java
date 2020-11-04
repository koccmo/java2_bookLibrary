package dental_clinic;

import dental_clinic.domain.Patient;
import dental_clinic.domain.PersonalData;
import dental_clinic.domain.ToothInfo;
import dental_clinic.domain.ToothStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientDatabaseImpl implements PatientDatabase {

    private Long id= 1L;
    private List<Patient> patientList = new ArrayList<>();


    @Override
    public boolean addPatient(PersonalData personalData) {
        if (cardListContainsPatient(personalData)){
            return false;
        }else
            {
            personalData.setId(id);
            patientList.add(new Patient(personalData));
            id++;
        }
        return true;
    }

    @Override
    public boolean deletePatient(long id) {
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPatient().getId() == id){
                patientList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean printDatabase() {
        patientList.forEach(System.out::println);
        return !patientList.isEmpty();
    }

    @Override
    public boolean printPatientHistory(long id) {
            for (int i = 0; i < patientList.size(); i++){
                if (isSpecificPatient(i, id)){
                    System.out.println(patientList.get(i).toString());
                    return true;
                }
            }
        return false;
    }

    @Override
    public List<PersonalData> findPatientBySurname(String surname) {
        return patientList.stream()
                .map(patient -> patient.getPatient())
                .filter(patientPersonalData -> patientPersonalData.getSurname().equalsIgnoreCase(surname))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonalData> findPatientByPersonalCode(String personalCode) {
        return patientList.stream()
                .map(patient -> patient.getPatient())
                .filter(patientPersonalData -> patientPersonalData.getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateJowlData(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus) {

        Optional <Patient> patientsCard = patientList.stream()
                .filter(patient -> patient.getPatient().getId() == id)
                .findFirst();
        if (patientsCard.isPresent()){
            patientsCard.get().updateJowl(toothNumber, comment, toothStatus);
            updatePatientsCard(patientsCard.get(), id);
            if (patientsCard.get().getJowl().keySet().contains(toothNumber)) return true;
            else return false;
        }
        return false;
    }

    @Override
    public boolean printPatientCardForVisit(long id) {
        for (int i = 0; i < patientList.size(); i++){
            if (isSpecificPatient(i, id)){
                System.out.println(patientList.get(i).getPatient());
                printActualInfoAboutJowl(i);
                return true;
            }
        }
        return false;
    }

    private boolean cardListContainsPatient (PersonalData personalData){
        Optional <PersonalData> result = patientList.stream()
                .map(patient -> patient.getPatient())
                .filter(patient1 -> patient1.equals(personalData))
                .findAny();
        return result.isPresent();
    }

    private void updatePatientsCard(Patient patientsPatient, long id){
        for (int i = 0; i < patientList.size(); i++){
            if (patientList.get(i).getPatient().getId() == id){
                patientList.set(i, patientsPatient);
            }
        }
    }

    private boolean cardListContainsId (long id){
        Optional <PersonalData> result = patientList.stream()
                .map(patient -> patient.getPatient())
                .filter(patient1 -> patient1.getId() == id)
                .findAny();
        return result.isPresent();
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientList.get(index).getPatient().getId() == id;
    }

    private void printActualInfoAboutJowl(int index){
        Map<Integer, ToothInfo> result = patientList.get(index).getJowl();
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

        System.out.println(toothInfoForPrint);
    }
}
