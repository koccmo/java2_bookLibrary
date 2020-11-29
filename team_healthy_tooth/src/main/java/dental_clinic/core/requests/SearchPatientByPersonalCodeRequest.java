package dental_clinic.core.requests;


public class SearchPatientByPersonalCodeRequest {

    private String personalCodeToSearch;

    public SearchPatientByPersonalCodeRequest(String personalCodeToSearch) {
        this.personalCodeToSearch = personalCodeToSearch;
    }

    public String getPersonalCodeToSearch() {
        return this.personalCodeToSearch;
    }

}
