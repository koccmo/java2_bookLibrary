package internet_store_1.core.requests.product;

import internet_store_1.core.domain.Product;

public class AddProductRequest {

    private Product product;

    public AddProductRequest(Product product){
        this.product = product;
    }

    public Product getProduct(){
        return product;
    }
}
