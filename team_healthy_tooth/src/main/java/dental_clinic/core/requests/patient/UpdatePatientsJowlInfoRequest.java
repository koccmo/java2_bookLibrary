package dental_clinic.core.requests.patient;

import dental_clinic.core.domain.ToothStatus;

import java.util.Map;

public class UpdatePatientsJowlInfoRequest {

    private Long id;
    private Integer toothNumber;
    private ToothStatus toothStatus;

    public UpdatePatientsJowlInfoRequest() { }

    public UpdatePatientsJowlInfoRequest(Long id, Integer toothNumber, ToothStatus toothStatus) {
        this.id = id;
        this.toothNumber = toothNumber;
        this.toothStatus = toothStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}