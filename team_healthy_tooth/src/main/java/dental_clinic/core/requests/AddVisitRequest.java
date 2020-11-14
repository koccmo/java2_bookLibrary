package dental_clinic.core.requests;

import dental_clinic.core.domain.ToothStatus;

import java.util.Optional;

public class AddVisitRequest {

    private long id;
    private int toothNumber;
    private Optional<String>comment;
    private ToothStatus toothStatus;
    private String doctor;

    public AddVisitRequest(long id, int toothNumber, Optional<String>comment, ToothStatus toothStatus, String doctor){
        this.id = id;
        this.toothNumber = toothNumber;
        this.comment = comment;
        this.toothStatus = toothStatus;
        this.doctor = doctor;
    }

    public long getId() {
        return id;
    }

    public int getToothNumber() {
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
}
