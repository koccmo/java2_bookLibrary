package internet_store.application.console_ui;

import internet_store.application.core.domain.Product;
import internet_store.application.core.responses.PrintProductsToConsoleResponse;
import internet_store.application.core.services.GetProductListService;

import java.util.List;

public class PrintProductsToConsoleUIAction implements UIAction {

    private final GetProductListService getProductListService;

    public PrintProductsToConsoleUIAction(GetProductListService getProductListService) {
        this.getProductListService = getProductListService;
    }

    public void execute() {
        PrintProductsToConsoleResponse productResponse =getProductListService.getProductList();
        List<Product> productList = productResponse.getProductList();
        if (productList.isEmpty()){
            System.out.println("Database is empty.");
        }else {
            productList.forEach(System.out::println);
        }
    }

}
