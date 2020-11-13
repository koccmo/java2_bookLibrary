package estore.ui;

import estore.database.ProductDataBase;
import estore.service.PrintListService;

public class ShowAllProductsUI implements UIAction {

    private ProductDataBase database;

    public ShowAllProductsUI(ProductDataBase database) {
        this.database = database;
    }

    @Override
    public void execute() {
        PrintListService.printListOfProducts(database.getDatabase());
    }
}
