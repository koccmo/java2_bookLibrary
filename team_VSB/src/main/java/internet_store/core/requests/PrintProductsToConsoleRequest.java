package internet_store.core.requests;

import internet_store.core.domain.Product;

import java.util.List;

public class PrintProductsToConsoleRequest {

    private final List<Product> productList;

    public PrintProductsToConsoleRequest(List<Product> productList) { this.productList = productList; }

    public List<Product> getProductList() { return productList; }

}
