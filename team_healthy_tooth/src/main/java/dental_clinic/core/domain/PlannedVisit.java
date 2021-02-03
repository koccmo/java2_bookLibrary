package dental_clinic.core.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="plannedVisit")
public class PlannedVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PersonalData personalData;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateAndTime")
    private Date visitTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public PlannedVisit() { }

    public PlannedVisit(Date visitTime, PersonalData personalData, Doctor doctor) {
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

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Date getVisitTime() {
        return  visitTime;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
