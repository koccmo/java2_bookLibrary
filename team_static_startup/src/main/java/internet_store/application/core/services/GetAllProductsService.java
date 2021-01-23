package internet_store.application.core.services;

import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.GetAllProductsRequest;
import internet_store.application.core.responses.GetAllProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductsService {

    @Autowired
    private ProductRepository productRepository;

 public GetAllProductsResponse execute(GetAllProductsRequest request) {
        List<Product> productList = productRepository.getProductList();
        return new GetAllProductsResponse(productList);
    }

}
