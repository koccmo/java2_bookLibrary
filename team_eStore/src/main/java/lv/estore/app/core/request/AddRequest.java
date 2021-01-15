package lv.estore.app.core.request;

public class AddRequest {
    private final String name;
    private final String description;
    private final String price;

    public AddRequest(final String name, final String description, final String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
