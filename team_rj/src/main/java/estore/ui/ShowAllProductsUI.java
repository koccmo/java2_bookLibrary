package estore.ui;

import estore.core.requests.GetAllProductsRequest;
import estore.core.responses.GetAllProductsResponse;
import estore.core.service.PrintListService;
import estore.core.service.GetAllProductsService;
import org.springframework.stereotype.Component;

@Component
public class ShowAllProductsUI implements UIAction {

    private GetAllProductsService showAllProductsService;

    public ShowAllProductsUI(GetAllProductsService showAllProductsService) {
        this.showAllProductsService = showAllProductsService;
    }

    @Override
    public void execute() {
        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = showAllProductsService.execute(request);
        PrintListService.printListOfProducts(response.getProducts());
    }
}
