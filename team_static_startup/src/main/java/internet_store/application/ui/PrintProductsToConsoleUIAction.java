package internet_store.application.ui;

import internet_store.application.domain.Product;
import internet_store.application.database.Database;

import java.util.List;

public class PrintProductsToConsoleUIAction implements UIAction {

    private final Database database;

    public PrintProductsToConsoleUIAction(Database database) {
        this.database = database;
    }

    public void execute() {
        List<Product> productList = database.getProductList();
        for (Product product : productList) {
            System.out.print(product.toString() + "\n");
        }
    }

}
