package lv.estore.app.core.request.products;

public class ProductNameRequest {

    private String name;

    public ProductNameRequest() {
    }

    public ProductNameRequest(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
