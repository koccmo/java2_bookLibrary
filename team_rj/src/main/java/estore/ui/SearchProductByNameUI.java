package estore.ui;

import estore.core.requests.SearchProductByNameRequest;
import estore.core.responses.SearchProductByNameResponse;
import estore.core.service.PrintListService;
import estore.core.service.SearchProductByNameService;

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
