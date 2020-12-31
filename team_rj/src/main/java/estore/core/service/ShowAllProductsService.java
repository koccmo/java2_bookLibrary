package estore.core.service;

import estore.database.ProductDB;
import estore.dependency_injection.DIComponent;
import estore.dependency_injection.DIDependency;
import estore.domain.Product;
import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.ShowAllProductsResponse;

import java.util.List;

@DIComponent
public class ShowAllProductsService {

    @DIDependency
    private ProductDB productDB;

//    public ShowAllProductsService(ProductDB productDB) {
//        this.productDB = productDB;
//    }

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        List<Product> foundProducts = productDB.getDatabase();
        return new ShowAllProductsResponse(foundProducts);
    }

}
