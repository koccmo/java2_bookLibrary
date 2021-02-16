package lv.estore.app.console_ui.products;

import lv.estore.app.core.request.products.GetAllProductsRequest;
import lv.estore.app.core.responses.products.GetProductsResponse;
import lv.estore.app.core.services.products.GetAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUIAction implements UIAction{

    @Autowired
    private GetAllProductsService getAllProductsService;

    public void execute() {
        System.out.println("The list of the products store contains: ");

        GetAllProductsRequest request = new GetAllProductsRequest();
        GetProductsResponse response = getAllProductsService.execute(request);
        response.getProducts().forEach(System.out::println);

        System.out.println("Press 'Enter' to continue.");
    }
}
