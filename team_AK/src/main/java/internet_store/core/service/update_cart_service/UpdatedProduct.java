package internet_store.core.service.update_cart_service;

import internet_store.core.domain.Product;
import lombok.Getter;

import java.math.BigDecimal;

class UpdatedProduct {
    @Getter
    private final Product product;
    @Getter
    private final BigDecimal newQuantity;
    @Getter
    private final int indexInCartDatabase;

    public UpdatedProduct(Product product, BigDecimal newQuantity, int indexInCartDatabase) {
        this.product = product;
        this.newQuantity = newQuantity;
        this.indexInCartDatabase = indexInCartDatabase;
    }
}