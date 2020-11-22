package internet_store.core.requests;

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

    public boolean emptyBothFields (){
        return (orderBy == null || orderBy.isEmpty()) && (orderDirection == null || orderDirection.isEmpty());
    }

    public boolean filledBoth(){
        return (orderBy != null && !orderBy.isEmpty()) && (orderDirection != null && !orderDirection.isEmpty());
    }
}
