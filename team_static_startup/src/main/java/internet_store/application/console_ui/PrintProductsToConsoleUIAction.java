package internet_store.application.console_ui;

import internet_store.application.core.domain.Product;
import internet_store.application.core.services.GetProductListService;

import java.util.List;

public class PrintProductsToConsoleUIAction implements UIAction {

    private final GetProductListService getProductListService;

    public PrintProductsToConsoleUIAction(GetProductListService getProductListService) {
        this.getProductListService = getProductListService;
    }

    public void execute() {
        List<Product> productList = getProductListService.getProductList();
        for (Product product : productList) {
            System.out.print(product.toString() + "\n");
        }
    }

}
