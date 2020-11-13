package estore.core.service;

import estore.database.ProductDataBase;
import estore.core.requests.RemoveProductByNameRequest;
import estore.core.responses.RemoveProductByNameResponse;

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
