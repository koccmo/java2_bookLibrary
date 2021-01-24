package dental_clinic.core.domain;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

public class PlannedVisit {

    private Long id;
    private PersonalData personalData;
    private GregorianCalendar visitTime;
    private Doctor doctor;

    public PlannedVisit(GregorianCalendar visitTime, PersonalData personalData, Doctor doctor) {
        this.visitTime = visitTime;
        this.personalData = personalData;
        this.doctor = doctor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setVisitTime(GregorianCalendar visitTime) {
        this.visitTime = visitTime;
    }

    public GregorianCalendar getVisitTime() {
        return  visitTime;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlannedVisit that = (PlannedVisit) o;
        return Objects.equals(visitTime, that.visitTime) && Objects.equals(personalData, that.personalData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitTime, personalData, doctor);
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm");
        return  "Planned visit:\n" + simpleDateFormat.format(visitTime.getTime()) + " "
                + personalData.getName() + " " + personalData.getSurname() + "\n"
                + "Dr." + doctor.getSurname();
    }
}
