package lv.estore.app.core.services;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.database.iDatabase;
import lv.estore.app.core.request.GetAllRequest;
import lv.estore.app.core.responses.GetAllResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllService {

    @Autowired
    iDatabase database;

    /**
     * Method to find all products.
     * @param request CoreRequest
     * @return GetAllResponse
     */
    public GetAllResponse execute(final GetAllRequest request) {
        List<Product> products = database.getAllProducts();
        return new GetAllResponse(products);
    }
}
