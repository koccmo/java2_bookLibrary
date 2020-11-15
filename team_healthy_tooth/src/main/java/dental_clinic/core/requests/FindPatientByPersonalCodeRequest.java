package dental_clinic.core.requests;


public class FindPatientByPersonalCodeRequest {

    private String personalCodeToSearch;

    public FindPatientByPersonalCodeRequest(String personalCodeToSearch) {
        this.personalCodeToSearch = personalCodeToSearch;
    }

    public String getPersonalCodeToSearch() {
        return this.personalCodeToSearch;
    }

}
