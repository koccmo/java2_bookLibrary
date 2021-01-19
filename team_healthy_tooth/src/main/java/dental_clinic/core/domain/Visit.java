package dental_clinic.core.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class Visit {

    private Long patientsId;
    private Integer toothNumber;
    private Optional<String> comment;
    ToothStatus toothStatus;
    private Doctor doctor;
    private Date date;

    public Visit (Long id, Integer toothNumber, Optional<String> comment, ToothStatus toothStatus, Doctor doctor, Date date){
        this.patientsId = id;
        this.toothNumber = toothNumber;
        this.comment = comment;
        this.toothStatus = toothStatus;
        this.doctor = doctor;
        this.date = date;
    }

    public Integer getToothNumber() {
        return toothNumber;
    }

    public Optional<String> getComment() {
        return comment;
    }

    public ToothStatus getToothStatus() {
        return toothStatus;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getDate() {
        return  date;
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
                "Patient's id: " + patientsId +
                ", Tooth number: " + toothNumber +
                ", Comment: " + comment +
                ", Tooth status: " + toothStatus +
                ", Dr." + doctor.getSurname() +
                ", Date: " + simpleDateFormat.format(date);
    }
}