package internet_store.core.requests.product;

public class ChangeProductRequest {

    private Long id;
    private String title;
    private String description;
    private Integer price;

    public ChangeProductRequest(Long id, String title, String description, Integer price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }
}
