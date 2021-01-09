package dental_clinic.core.requests;

import dental_clinic.core.domain.OrderingDirection;

public class Ordering {

    private String orderBy;
    private OrderingDirection orderDirection;

    public Ordering(String orderBy, OrderingDirection orderDirection) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public OrderingDirection getOrderDirection() {
        return orderDirection;
    }

    public boolean emptyBothFields (){
        return (orderBy == null || orderBy.isEmpty()) && (orderDirection == OrderingDirection.NULL);
    }

    public boolean filledBoth(){
        return (orderBy != null && !orderBy.isEmpty()) && (orderDirection != OrderingDirection.NULL);
    }

    public boolean filledOne() {
        return ((orderBy == null || orderBy.isEmpty()) && (orderDirection != OrderingDirection.NULL)) ||
                ((orderBy != null) && (orderDirection == OrderingDirection.NULL));
    }
}
