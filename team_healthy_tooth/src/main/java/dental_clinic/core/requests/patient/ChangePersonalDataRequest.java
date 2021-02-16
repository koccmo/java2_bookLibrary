package dental_clinic.core.requests.patient;

public class ChangePersonalDataRequest {

    private Long patientIdNumber;
    private String updatedSurname;
    private String updatedPhoneNumber;

    public ChangePersonalDataRequest() { }
    public ChangePersonalDataRequest(Long patientIdNumber,
                                     String updatedSurname,
                                     String updatedPhoneNumber) {
        this.patientIdNumber = patientIdNumber;
        this.updatedSurname = updatedSurname;
        this.updatedPhoneNumber = updatedPhoneNumber;
    }

    public Long getPatientIdNumber() {
        return patientIdNumber;
    }

    public void setUpdatedSurname(String updatedSurname) {
        this.updatedSurname = updatedSurname;
    }

    public void setPatientIdNumber(Long patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public void setUpdatedPhoneNumber(String updatedPhoneNumber) {
        this.updatedPhoneNumber = updatedPhoneNumber;
    }

    public String getUpdatedSurname() {
        return updatedSurname;
    }

    public String getUpdatedPhoneNumber() {
        return updatedPhoneNumber;
    }

}