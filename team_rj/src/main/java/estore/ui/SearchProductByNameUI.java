package estore.ui;

import estore.database.ProductDataBase;
import estore.requests.SearchProductByNameRequest;
import estore.responses.SearchProductByNameResponse;
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

        SearchProductByNameRequest request = new SearchProductByNameRequest(productToSearch);
        SearchProductByNameResponse response = searchProductByNameService.execute(request);

        PrintListService.printListOfProducts(response.getProducts());
    }
}
