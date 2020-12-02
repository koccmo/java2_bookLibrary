package internet_store.application.console_ui;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.GetAllProductsRequest;
import internet_store.application.core.responses.GetAllProductsResponse;
import internet_store.application.core.services.GetAllProductsService;

import java.util.List;

public class GetAllProductsUIAction implements UIAction {

    private final GetAllProductsService getAllProductsService;

    public GetAllProductsUIAction(GetAllProductsService getAllProductsService) {
        this.getAllProductsService = getAllProductsService;
    }

    @Override
    public void execute() {
        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse productResponse = getAllProductsService.execute(request);
        List<Product> productList = productResponse.getProductList();
        if (productList.isEmpty()){
            System.out.println("Database is empty.");
        }else {
            productList.forEach(System.out::println);
        }
    }

}
