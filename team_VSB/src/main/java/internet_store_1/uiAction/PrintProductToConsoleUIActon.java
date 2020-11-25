package internet_store_1.uiAction;

import internet_store_1.core.domain.Product;
import internet_store_1.core.responses.PrintProductToConsoleResponse;
import internet_store_1.core.services.GetProductListService;

import java.util.List;

public class PrintProductToConsoleUIActon implements UIAction {

    private final GetProductListService getProductListService;

    public PrintProductToConsoleUIActon(GetProductListService getProductListService) {
        this.getProductListService = getProductListService;
    }

    public void execute() {
        PrintProductToConsoleResponse response = getProductListService.getProductList();
        List<Product> productList = response.getProductList();
        if (productList.isEmpty()) {
            System.out.println("Database is empty!");
        } else {
            productList.forEach(System.out::println);
        }
    }
}
