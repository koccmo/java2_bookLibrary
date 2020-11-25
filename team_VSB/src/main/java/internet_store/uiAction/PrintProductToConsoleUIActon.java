package internet_store.uiAction;

import internet_store.core.domain.Product;
import internet_store.core.responses.PrintProductToConsoleResponse;
import internet_store.core.services.GetProductListService;

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
