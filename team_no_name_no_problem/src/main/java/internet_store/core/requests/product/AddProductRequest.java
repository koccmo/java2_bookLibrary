package internet_store.core.requests.product;

import internet_store.core.domain.Product;

public class AddProductRequest {

    private Product product;

    public AddProductRequest(Product product){
        this.product = product;
    }

    public AddProductRequest() { }

    public Product getProduct(){
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
