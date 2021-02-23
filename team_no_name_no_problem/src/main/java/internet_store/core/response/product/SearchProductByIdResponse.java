package internet_store.core.response.product;

import internet_store.core.domain.Product;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class SearchProductByIdResponse extends CoreResponse {

    private Product products;

    public SearchProductByIdResponse(Product product){
        this.products = product;
    }

    public SearchProductByIdResponse(List<CoreError> errors){
        super(errors);
    }

    public Product getProducts(){
        return products;
    }


}
