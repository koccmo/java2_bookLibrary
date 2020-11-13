package estore.ui;

import estore.database.ProductDataBase;
import estore.service.PrintListService;
import estore.domain.Product;

import java.util.List;

public class SearchProductByNameUI implements UIAction {

    private ProductDataBase database;
    private InputValidation iv;

    public SearchProductByNameUI(ProductDataBase database, InputValidation iv) {
        this.database = database;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter name of product to search: ";
        String productToSearch = iv.getString(description);
        List<Product> foundProducts = database.searchProductByName(productToSearch);
        PrintListService.printListOfProducts(foundProducts);
    }
}
