package internet_store.application.core.requests;

public class FindProductsRequest {

    private final String name;
    private final String description;
    private String orderBy;
    private String orderDirection;

    public FindProductsRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FindProductsRequest(String name, String description,
                               String orderBy, String orderDirection) {
        this.name = name;
        this.description = description;
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isDescriptionProvided() {
        return this.description != null && !this.description.isEmpty();
    }


}
