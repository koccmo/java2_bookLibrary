package dental_clinic.core.domain;

import java.util.*;

public class Patient {

    private PersonalData personalData;
    public JowlEntity jowlEntity;
    public List <Visit> visits = new ArrayList<>();

    public Patient(PersonalData personalData){
        this.personalData = personalData;
    }

    public Patient() {};

    public Patient (PersonalData personalData, JowlEntity jowl, List <Visit> visits) {
        this.personalData = personalData;
        this.jowlEntity = jowl;
        this.visits = visits;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public JowlEntity getJowl() {
        return jowlEntity;
    }

    public void setJowl(JowlEntity jowl) {
        this.jowlEntity = jowl;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void addVisit(Visit visit){
        visits.add(visit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(personalData, patient.personalData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalData);
    }

    @Override
    public String toString() {
        return "\nPatient:" +
                personalData +
                jowlEntity +
                "Visits: " + visits;
    }
}
