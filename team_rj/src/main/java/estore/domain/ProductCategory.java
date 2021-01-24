package estore.domain;

public class ProductCategory {

    private Long id;
    private String category;

    public ProductCategory(String category) {
        this.category = category;
    }

    public ProductCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "category='" + category + '\'' +
                '}';
    }
}
