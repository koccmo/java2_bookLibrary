package internet_store.core.responses;

import internet_store.core.domain.Product;

import java.util.List;

public class PrintProductToConsoleResponse extends CoreResponse {

    private List<Product> productList;

    public PrintProductToConsoleResponse(List<Product> productList) { this.productList = productList; }

    public List<Product> getProductList() { return productList; }
}
