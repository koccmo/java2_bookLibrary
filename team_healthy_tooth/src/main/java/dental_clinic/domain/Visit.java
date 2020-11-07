package dental_clinic.domain;

import java.util.Objects;
import java.util.Optional;

public class Visit {

    private int toothNumber;
    private Optional<String> comment;
    ToothStatus toothStatus;
    private String doctor;

    public Visit (int toothNumber, Optional<String> comment, ToothStatus toothStatus, String doctor){
        this.toothNumber = toothNumber;
        this.comment = comment;
        this.toothStatus = toothStatus;
        this.doctor = doctor;
    }

    public Optional<String> getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return toothNumber == visit.toothNumber &&
                Objects.equals(comment, visit.comment) &&
                toothStatus == visit.toothStatus &&
                Objects.equals(doctor, visit.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toothNumber, comment, toothStatus, doctor);
    }

    @Override
    public String toString() {
        return "\nVisit{" +
                "toothNumber=" + toothNumber +
                ", comment=" + comment +
                ", toothStatus=" + toothStatus +
                ", doctor='" + doctor + '\'' +
                '}';
    }
}
