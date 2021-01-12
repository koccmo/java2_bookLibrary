package lv.estore.app.core.request;

public class UpdateRequest {
    private final String id;
    private final String name;
    private final String description;
    private final String price;

    public UpdateRequest(final String id, final String name, final String description, final String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPrice() {
        return this.price;
    }

    public String getId() {
        return this.id;
    }

    public Ordering getOrdering(){
        return null;
    }
}
