package estore.core.service;

import estore.database.ProductDataBase;
import estore.core.requests.RemoveProductByIdRequest;
import estore.core.responses.RemoveProductByIdResponse;

public class RemoveProductByIdService {

    private ProductDataBase database;

    public RemoveProductByIdService(ProductDataBase database) {
        this.database = database;
    }

    public RemoveProductByIdResponse execute(RemoveProductByIdRequest request) {
        int productsRemoved = database.removeProductById(request.getProductId());
        return new RemoveProductByIdResponse(productsRemoved);
    }

}
