package internet_store.lesson_3.core.responses;

import internet_store.lesson_3.core.domain.Product;

import java.util.List;

public class PrintProductsToConsoleResponse extends CoreResponse {

    private List<Product> productList;

    public PrintProductsToConsoleResponse(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
