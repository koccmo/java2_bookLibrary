package dental_clinic.core.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PersonalData personalData;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "manipulation_id", nullable = false)
    private Manipulation manipulations;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateAndTime")
    private Date date;

    @Column(name = "sum")
    private Integer calculateSum;

    @Column(name = "tooth_number")
    private Integer toothNumber;

    @Column(name = "tooth_status")
    private ToothStatus toothStatus;

    @Column(name = "Remarks")
    private String comment;

    public Visit (PersonalData personalData, Integer toothNumber, String comment, ToothStatus toothStatus,
                  Doctor doctor, Manipulation manipulations, Date date, Integer sum){
        this.personalData = personalData;
        this.toothNumber = toothNumber;
        this.comment = comment;
        this.toothStatus = toothStatus;
        this.doctor = doctor;
        this.manipulations = manipulations;
        this.date = date;
        this.calculateSum = sum;
    }

    public Visit () { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Manipulation getManipulations() {
        return manipulations;
    }

    public void setManipulations(Manipulation manipulations) {
        this.manipulations = manipulations;
    }

    public Date getDate() {
        return  date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getToothNumber() {
        return toothNumber;
    }

    public void setToothNumber(Integer toothNumber) {
        this.toothNumber = toothNumber;
    }

    public ToothStatus getToothStatus() {
        return toothStatus;
    }

    public void setToothStatus(ToothStatus toothStatus) {
        this.toothStatus = toothStatus;
    }

    public Integer getCalculateSum() {
        return calculateSum;
    }

    public void setCalculateSum(Integer calculateSum) {
        this.calculateSum = calculateSum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(toothNumber, visit.toothNumber) &&
                Objects.equals(comment, visit.comment) &&
                toothStatus == visit.toothStatus &&
                Objects.equals(doctor, visit.doctor) &&
                Objects.equals(date, visit.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toothNumber, comment, toothStatus, doctor, date);
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return "\nVisit: " +
                "Patient's id: " + personalData +
                ", Tooth number: " + toothNumber +
                ", Comment: " + comment +
                ", Tooth status: " + toothStatus +
                ", Dr." + doctor.getSurname() +
                ", Date: " + simpleDateFormat.format(date);
    }
}