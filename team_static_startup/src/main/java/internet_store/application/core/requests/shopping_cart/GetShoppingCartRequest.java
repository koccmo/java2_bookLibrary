package internet_store.application.core.requests.shopping_cart;

public class GetShoppingCartRequest {

    private Long id;

    public GetShoppingCartRequest() {
    }

    public GetShoppingCartRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
