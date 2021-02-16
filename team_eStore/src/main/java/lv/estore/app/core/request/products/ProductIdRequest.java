package lv.estore.app.core.request.products;

public class ProductIdRequest {

    private String id;

    public ProductIdRequest() {

    }

    public ProductIdRequest(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
