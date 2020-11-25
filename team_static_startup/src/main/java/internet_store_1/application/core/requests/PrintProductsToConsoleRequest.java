package internet_store_1.application.core.requests;

import internet_store_1.lesson_1.Product;

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
