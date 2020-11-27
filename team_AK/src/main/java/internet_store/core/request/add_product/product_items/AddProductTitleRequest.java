package internet_store.core.request.add_product.product_items;

import lombok.Getter;

public class AddProductTitleRequest {
    @Getter
    private final String productTitle;

    public AddProductTitleRequest(String productTitle) {
        this.productTitle = productTitle;
    }
}
