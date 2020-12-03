package core.request;

public class AddRequest extends CoreRequest {
    private String name;
    private String description;
    private String price;

    public AddRequest(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getPrice() {
        return this.price;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
    public String getOrderDirection() {
        return null;
    }
}
