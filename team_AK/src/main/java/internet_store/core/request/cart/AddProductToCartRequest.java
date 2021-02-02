package internet_store.core.request.cart;

import lombok.Getter;

public class AddProductToCartRequest {
    @Getter
    private final long id;
    @Getter
    private final Long newQuantity;
    @Getter
    private final Object database;
    @Getter
    private final String productTitle;

    public AddProductToCartRequest(long id, long newQuantity, Object database, String productTitle) {
        this.id = id;
        this.newQuantity = newQuantity;
        this.database = database;
        this.productTitle = productTitle;
    }
}