package lv.estore.app.core.request.deals;

public class AnyIdInDealRequest {

    private String id;

    public AnyIdInDealRequest() {

    }

    public AnyIdInDealRequest(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
