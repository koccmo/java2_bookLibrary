package estore.core.service;

import estore.database.ProductDB;
import estore.domain.Product;
import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.ShowAllProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllProductsService {

    @Autowired
    private ProductDB productDB;

//    public ShowAllProductsService(ProductDB productDB) {
//        this.productDB = productDB;
//    }

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        List<Product> foundProducts = productDB.getDatabase();
        return new ShowAllProductsResponse(foundProducts);
    }

}
