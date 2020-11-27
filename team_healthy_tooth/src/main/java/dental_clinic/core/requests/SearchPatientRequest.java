package dental_clinic.core.requests;

public class SearchPatientRequest {

    private String name;
    private String surname;
    private Ordering ordering;
    private Paging paging;

    public SearchPatientRequest(String name, String surname, Ordering ordering, Paging paging) {
        this.name = name;
        this.surname = surname;
        this.ordering = ordering;
        this.paging = paging;
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

    public Paging getPaging(){
        return paging;
    }

}
