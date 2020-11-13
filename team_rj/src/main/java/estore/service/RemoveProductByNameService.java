package estore.service;

import estore.database.ProductDataBase;
import estore.requests.RemoveProductByNameRequest;
import estore.responses.RemoveProductByNameResponse;

public class RemoveProductByNameService {

    private ProductDataBase database;

    public RemoveProductByNameService(ProductDataBase database) {
        this.database = database;
    }

    public RemoveProductByNameResponse execute(RemoveProductByNameRequest request) {
        int productsRemoved = database.removeProductByName(request.getProductName());
        return new RemoveProductByNameResponse(productsRemoved);
    }

}
