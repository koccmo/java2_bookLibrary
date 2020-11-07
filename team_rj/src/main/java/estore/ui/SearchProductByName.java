package estore.ui;

import estore.database.ProductDataBase;
import estore.domain.Product;
import estore.service.PrintList;

import java.util.List;

public class SearchProductByName implements UIAction {

    private ProductDataBase database;
    private InputValidation iv;

    public SearchProductByName(ProductDataBase database, InputValidation iv) {
        this.database = database;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter name of product to search: ";
        String productToSearch = iv.getString(description);
        List<Product> foundProducts = database.searchProductByName(productToSearch);
        PrintList.printListOfProducts(foundProducts);
    }
}
