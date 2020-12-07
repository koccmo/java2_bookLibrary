package internet_store.core.response.product;

import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class BuyProductResponse extends CoreResponse {

    private Long id;
    private Integer quantity;

    public BuyProductResponse(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public BuyProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }

    private Integer getQuantity(){
        return quantity;
    }
}
