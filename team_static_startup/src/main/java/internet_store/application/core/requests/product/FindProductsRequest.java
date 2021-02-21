package internet_store.application.core.requests.product;

public class FindProductsRequest {

    private String name;
    private String description;
    private Ordering ordering;
    private Paging paging;

    public FindProductsRequest() { }

    public FindProductsRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FindProductsRequest(String name, String description, Ordering ordering) {
        this.name = name;
        this.description = description;
        this.ordering = ordering;
    }

    public FindProductsRequest(String name, String description, Paging paging) {
        this.name = name;
        this.description = description;
        this.paging = paging;
    }

    public FindProductsRequest(String name, String description, Ordering ordering, Paging paging) {
        this.name = name;
        this.description = description;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isDescriptionProvided() {
        return this.description != null && !this.description.isEmpty();
    }


}
