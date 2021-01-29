package estore.core.responses;

import estore.core.domain.ProductCategory;

import java.util.List;

public class GetAllProductCategoriesResponse {

    private List<ProductCategory> categories;

    public GetAllProductCategoriesResponse(List<ProductCategory> categories) {
        this.categories = categories;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }
}
