package lv.estore.app.core.services.products;

import lv.estore.app.core.database.products.ProductRepository;
import lv.estore.app.core.domain.Product;
import lv.estore.app.core.request.products.GetAllProductsRequest;
import lv.estore.app.core.responses.products.GetProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductsService {

    @Autowired
    ProductRepository database;

    public GetProductsResponse execute(final GetAllProductsRequest request) {
        List<Product> products = database.getAllProducts();
        return new GetProductsResponse(null, products);
    }
}
