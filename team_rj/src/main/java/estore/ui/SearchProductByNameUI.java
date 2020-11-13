package estore.ui;

import estore.database.ProductDataBase;
import estore.service.PrintListService;
import estore.domain.Product;
import estore.service.SearchProductByNameService;

import java.util.List;

public class SearchProductByNameUI implements UIAction {

    private SearchProductByNameService searchProductByNameService;
    private InputValidation iv;

    public SearchProductByNameUI(SearchProductByNameService searchProductByNameService, InputValidation iv) {
        this.searchProductByNameService = searchProductByNameService;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter name of product to search: ";
        String productToSearch = iv.getString(description);
        List<Product> foundProducts = searchProductByNameService.execute(productToSearch);
        PrintListService.printListOfProducts(foundProducts);
    }
}
