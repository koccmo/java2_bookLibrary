package internet_store.core.response.product;

import internet_store.core.domain.Product;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class BuyProductResponse extends CoreResponse {

    private Product product;
    private Integer quantity;
    private String endOfShopping;

    public BuyProductResponse(Product product, Integer quantity, String endOfShopping) {
        this.product = product;
        this.quantity = quantity;
        this.endOfShopping = endOfShopping;
    }

    public BuyProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public Product getProduct() {
        return product;
    }

    private Integer getQuantity(){
        return quantity;
    }

    private String getEndOfShopping() {
        return endOfShopping;
    }
}
