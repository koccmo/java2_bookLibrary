package internet_store.core.request.cart;

import lombok.Getter;

import java.math.BigDecimal;

public class UpdateCartRequest {
    @Getter
    private final long id;
    @Getter
    private final BigDecimal newQuantity;

    public UpdateCartRequest(long id, BigDecimal newQuantity) {
        this.id = id;
        this.newQuantity = newQuantity;
    }
}