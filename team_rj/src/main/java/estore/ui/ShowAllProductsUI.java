package estore.ui;

import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.ShowAllProductsResponse;
import estore.core.service.PrintListService;
import estore.core.service.ShowAllProductsService;

public class ShowAllProductsUI implements UIAction {

    private ShowAllProductsService showAllProductsService;

    public ShowAllProductsUI(ShowAllProductsService showAllProductsService) {
        this.showAllProductsService = showAllProductsService;
    }

    @Override
    public void execute() {
        ShowAllProductsRequest request = new ShowAllProductsRequest();
        ShowAllProductsResponse response = showAllProductsService.execute(request);
        PrintListService.printListOfProducts(response.getProducts());
    }
}
