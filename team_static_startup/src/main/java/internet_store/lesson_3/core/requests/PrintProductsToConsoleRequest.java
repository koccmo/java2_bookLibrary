package internet_store.lesson_3.core.requests;

import internet_store.lesson_3.core.domain.Product;

import java.util.List;

class PrintProductsToConsoleRequest {

    private final List<Product> productList;

    public PrintProductsToConsoleRequest(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

}
