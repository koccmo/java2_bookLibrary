package internet_store_1.core.responses;

import internet_store_1.core.domain.Product;

import java.util.List;

public class PrintProductToConsoleResponse extends CoreResponse {

    private List<Product> productList;

    public PrintProductToConsoleResponse(List<Product> productList) { this.productList = productList; }

    public List<Product> getProductList() { return productList; }
}
