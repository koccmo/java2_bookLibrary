package core.request;

public class NameRequest extends CoreRequest{
    private String name;

    public NameRequest(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getPrice() {
        return null;
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
