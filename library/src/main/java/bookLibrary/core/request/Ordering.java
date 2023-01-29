package bookLibrary.core.request;

public class Ordering {
    private static String firstFieldForOrderBy;
    private static String secondFieldForOrderBy;
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

    public  String getFirstField() {
        return firstFieldForOrderBy;
    }

    public void setFirstField(String firstField) {
        Ordering.firstFieldForOrderBy = firstField;
    }

    public  String getSecondField() {
        return secondFieldForOrderBy;
    }

    public void setSecondField(String secondField) {
        Ordering.secondFieldForOrderBy = secondField;
    }
}
