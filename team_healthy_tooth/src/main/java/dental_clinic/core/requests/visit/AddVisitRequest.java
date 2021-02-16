package dental_clinic.core.requests.visit;

import dental_clinic.core.domain.*;

import java.util.Date;

public class AddVisitRequest {

    private Long patientId;
    private Long manipulationId;
    private Long doctorId;
    private Integer toothNumber;
    private ToothStatus toothStatus;
    private String comment;

    public AddVisitRequest() { }

    public AddVisitRequest(Long patientId, Long manipulationId, Long doctorId,
                           Integer toothNumber, ToothStatus toothStatus, String comment) {
        this.patientId = patientId;
        this.manipulationId = manipulationId;
        this.doctorId = doctorId;
        this.toothNumber = toothNumber;
        this.toothStatus = toothStatus;
        this.comment = comment;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getManipulationId() {
        return manipulationId;
    }

    public void setManipulationId(Long manipulationId) {
        this.manipulationId = manipulationId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
