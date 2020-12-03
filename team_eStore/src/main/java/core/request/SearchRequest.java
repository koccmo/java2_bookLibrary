package core.request;

public class SearchRequest extends CoreRequest{
    private String name;
    private String price;
    private String orderBy;
    private String orderDirection;

    public SearchRequest(final String name, final String price) {
        this.name = name;
        this.price = price;
    }

    public SearchRequest(final String name, final String price, final String orderBy, final String orderDirection){
        this.name = name;
        this.price = price;
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getOrderBy(){
        return orderBy;
    }

    @Override
    public String getOrderDirection(){
        return orderDirection;
    }
}
