package internet_store.application.ui;

import internet_store.application.domain.Product;
import internet_store.application.database.Database;
import internet_store.application.services.GetProductListService;

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
