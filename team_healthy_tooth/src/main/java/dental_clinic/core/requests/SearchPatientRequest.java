package dental_clinic.core.requests;

public class SearchPatientRequest {

    private String name;
    private String surname;
    private Ordering ordering;

    public SearchPatientRequest(String name, String surname, Ordering ordering) {
        this.name = name;
        this.surname = surname;
        this.ordering = ordering;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Ordering getOrdering(){
        return ordering;
    }

}
