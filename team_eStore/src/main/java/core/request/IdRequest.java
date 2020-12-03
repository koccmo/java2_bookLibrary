package core.request;

public class IdRequest extends CoreRequest{
    private Long id;

    public IdRequest(final long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return null;
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
        return this.id;
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
