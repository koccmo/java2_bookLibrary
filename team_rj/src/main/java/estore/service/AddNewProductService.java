package estore.service;

import estore.database.ProductDataBase;
import estore.domain.Product;
import estore.requests.AddNewProductRequest;
import estore.responses.AddNewProductResponse;

public class AddNewProductService {

    private ProductDataBase database;

    public AddNewProductService(ProductDataBase database) {
        this.database = database;
    }

    public AddNewProductResponse execute(AddNewProductRequest request) {
        Product product = new Product(request.getProductName(), request.getProductDescription());
        database.addNewProduct(product);
        AddNewProductResponse response = new AddNewProductResponse(product);
        response.setSuccessfullyAdded(true);
        return response;
    }

}
