package dental_clinic.core.requests;

public class SearchPatientRequest {

    private String name;
    private String surname;

    public SearchPatientRequest(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}
