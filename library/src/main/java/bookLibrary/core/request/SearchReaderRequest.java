package bookLibrary.core.request;

public class SearchReaderRequest {
    private String name;
    private String lastName;
    private Ordering ordering;
    private Paging paging;


    public SearchReaderRequest(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public SearchReaderRequest(String name, String lastName, Ordering ordering, Paging paging) {
        this.name = name;
        this.lastName = lastName;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
