package internet_store.lesson_6.core.services;

import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.domain.Product;
import internet_store.lesson_6.core.requests.GetAllProductsRequest;
import internet_store.lesson_6.core.responses.GetAllProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductsService {

    @Autowired
    private Database database;

 public GetAllProductsResponse execute(GetAllProductsRequest request) {
        List<Product> productList = database.getProductList();
        return new GetAllProductsResponse(productList);
    }

}
