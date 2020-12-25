package internet_store.lesson_6.console_ui;

import internet_store.lesson_6.core.domain.Product;
import internet_store.lesson_6.core.requests.GetAllProductsRequest;
import internet_store.lesson_6.core.responses.GetAllProductsResponse;
import internet_store.lesson_6.core.services.GetAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductsUIAction implements UIAction {

    @Autowired
    private GetAllProductsService getAllProductsService;

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
