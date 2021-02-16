package internet_store.application.core.requests.shopping_cart;

public class FindShoppingCartByIdRequest {

    private Long id;

    public FindShoppingCartByIdRequest() { }

    public FindShoppingCartByIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
