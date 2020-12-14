package internet_store.core.requests.product;

public class BuyProductRequest {

    private Long id;
    private Integer quantity;
    private String endOfShopping;

    public BuyProductRequest(Long id, Integer quantity, String endOfShopping) {
        this.id = id;
        this.quantity = quantity;
        this.endOfShopping = endOfShopping;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getEndOfShopping() {
        return endOfShopping;
    }
}
