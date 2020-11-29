package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import lombok.Getter;

import java.math.BigDecimal;

class AddedProduct {
    @Getter
    private final Product product;
    @Getter
    private final BigDecimal newQuantity;

    public AddedProduct(Product product, BigDecimal newQuantity) {
        this.product = product;
        this.newQuantity = newQuantity;
    }
}