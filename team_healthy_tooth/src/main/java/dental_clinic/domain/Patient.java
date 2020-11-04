package dental_clinic.domain;

import java.util.*;

public class Patient {

    private PersonalData personalData;
    private Map  <Integer, ToothInfo> jowl;
    private List<String> attendingDoctors;

    public Patient(PersonalData personalData, String...attendingDoctors){
        this.personalData = personalData;
        jowl = createNewJowl();
        this.attendingDoctors = new ArrayList<>(Arrays.asList(attendingDoctors));
    }

    private Map <Integer, ToothInfo> createNewJowl(){
        Map <Integer, ToothInfo> newJowl = new HashMap<>();
        for (int i = 18; i>10; i--) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 21; i<29; i++) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 55; i>50; i--) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 61; i<66; i++) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }

        for (int i = 48; i>40; i--) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 31; i<39; i++) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 85; i>80; i--) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 71; i<76; i++) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        return newJowl;
    }

    public PersonalData getPatient() {
        return personalData;
    }

    public Map<Integer, ToothInfo> getJowl() {
        return jowl;
    }

    public List<String> getAttendingDoctors() {
        return attendingDoctors;
    }

    public void addAttendingDoctor(String attendingDoctor) {
        this.attendingDoctors.add(attendingDoctor);
    }

    public boolean updateJowl(int toothNumber, Optional <String> comment, ToothStatus toothStatus) {
        if (jowl.containsKey(toothNumber)) {
            jowl.get(toothNumber).addComment(comment);
            jowl.get(toothNumber).addStatus(toothStatus);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        String result = "Patient{ personal data=" + personalData;
       for (Integer key : jowl.keySet()){
           result += key + " " + jowl.get(key) + "\n";
       }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(personalData, patient.personalData) &&
                Objects.equals(jowl, patient.jowl) &&
                Objects.equals(attendingDoctors, patient.attendingDoctors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalData, jowl, attendingDoctors);
    }
}
