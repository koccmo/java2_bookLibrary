package dental_clinic.core.domain;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class Visit {

    private Integer toothNumber;
    private Optional<String> comment;
    ToothStatus toothStatus;
    private String doctor;
    private Date date;

    public Visit (Integer toothNumber, Optional<String> comment, ToothStatus toothStatus, String doctor, Date date){
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

    public String getDoctor() {
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
        return "\nVisit{" +
                "toothNumber=" + toothNumber +
                ", comment=" + comment +
                ", toothStatus=" + toothStatus +
                ", doctor='" + doctor + '\'' +
                ", date=" + date +
                '}';
    }
}
