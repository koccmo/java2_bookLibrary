package internet_store_1.core.requests.product;

public class ChangeDescriptionRequest {

    private long id;
    private String description;

    public ChangeDescriptionRequest(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
