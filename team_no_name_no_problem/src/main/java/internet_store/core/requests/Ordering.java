package internet_store.core.requests;

public class Ordering {

    public String orderBy;
    public String orderDirection;

    public Ordering(String orderBy, String orderDirection) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public Ordering() { }

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

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }
}
