package core.requests;

public class Ordering {

    private String orderBy;
    private String orderDirection;

    public Ordering(String orderBy, String orderDirection) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public boolean isAscending(String fieldName) {
        return getOrderDirection().equals("ascending") && getOrderBy().equals(fieldName);
    }
    public boolean isDescending(String fieldName) {
        return getOrderDirection().equals("descending") && getOrderBy().equals(fieldName);
    }

}
