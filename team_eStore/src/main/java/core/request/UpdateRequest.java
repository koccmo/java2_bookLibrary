package core.request;

public class UpdateRequest extends CoreRequest{
    private Long id;
    private String name;
    private String description;
    private String price;

    public UpdateRequest(Long id, String name, String description, String price) {
        this.id = id;
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
