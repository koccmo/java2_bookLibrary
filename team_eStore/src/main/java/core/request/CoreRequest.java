package core.request;

public abstract class CoreRequest {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getPrice();
    public abstract Long getId();
    public abstract String getOrderBy();
    public abstract String getOrderDirection();
}
