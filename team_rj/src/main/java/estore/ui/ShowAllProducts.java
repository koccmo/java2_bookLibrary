package estore.ui;

import estore.database.ProductDataBase;
import estore.service.PrintList;

public class ShowAllProducts implements UIAction {

    private ProductDataBase database;

    public ShowAllProducts(ProductDataBase database) {
        this.database = database;
    }

    @Override
    public void execute() {
        PrintList.printListOfProducts(database.getDatabase());
    }
}
