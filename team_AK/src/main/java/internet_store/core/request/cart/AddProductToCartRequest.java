package internet_store.core.request.cart;

import lombok.Getter;

import java.math.BigDecimal;

public class AddProductToCartRequest {
    @Getter
    private final long id;
    @Getter
    private final BigDecimal newQuantity;

    public AddProductToCartRequest(long id, BigDecimal newQuantity) {
        this.id = id;
        this.newQuantity = newQuantity;
    }
}