package lv.estore.app.core.request.products;

public class UpdateProductByIdRequest {
    private String id;
    private String name;
    private String description;
    private String price;

    public UpdateProductByIdRequest() {
    }

    public UpdateProductByIdRequest(final String id, final String name, final String description, final String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPrice() {
        return this.price;
    }

    public String getId() {
        return this.id;
    }

    public Ordering getOrdering(){
        return null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
