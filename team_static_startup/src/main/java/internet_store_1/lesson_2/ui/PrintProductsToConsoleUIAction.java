package internet_store_1.lesson_2.ui;

import internet_store_1.lesson_2.domain.Product;
import internet_store_1.lesson_2.services.GetProductListService;

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
