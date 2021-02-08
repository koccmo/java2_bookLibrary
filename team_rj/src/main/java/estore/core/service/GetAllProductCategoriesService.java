package estore.core.service;

import estore.core.requests.GetAllProductCategoriesRequest;
import estore.core.responses.GetAllProductCategoriesResponse;
import estore.database.ProductCategoryRepository;
import estore.core.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductCategoriesService {

    @Autowired
    private ProductCategoryRepository productCategoryDb;

    public GetAllProductCategoriesResponse execute(GetAllProductCategoriesRequest request) {
        List<ProductCategory> foundCategories = productCategoryDb.getDatabase();
        return new GetAllProductCategoriesResponse(foundCategories);
    }
}
