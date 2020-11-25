package internet_store_1.lesson_1.uiaction;

import internet_store_1.lesson_1.Product;
import internet_store_1.lesson_1.ProductDatabase;

import java.util.List;

public class PrintProductsToConsoleUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public PrintProductsToConsoleUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        List<Product> productList = productDatabase.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            System.out.print(productList.get(i).toString() + "\n");
        }
    }

}
