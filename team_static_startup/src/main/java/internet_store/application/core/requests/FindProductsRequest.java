package internet_store.application.core.requests;

public class FindProductsRequest {

    private final String name;
    private final String description;
    private Ordering ordering;

    public FindProductsRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FindProductsRequest(String name, String description, Ordering ordering) {
        this.name = name;
        this.description = description;
        this.ordering = ordering;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isDescriptionProvided() {
        return this.description != null && !this.description.isEmpty();
    }


}
