package internet_store.lesson_5.console_ui;

import internet_store.lesson_5.core.domain.Product;
import internet_store.lesson_5.core.requests.GetAllProductsRequest;
import internet_store.lesson_5.core.responses.GetAllProductsResponse;
import internet_store.lesson_5.core.services.GetAllProductsService;

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
