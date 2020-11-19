package lesson_3.core.request.add_product;

import lesson_3.core.domain.Product;
import lombok.Getter;

public class AddProductRequest {
    @Getter
    private final Product product;

    public AddProductRequest(Product product) {
        this.product = product;
    }
}
