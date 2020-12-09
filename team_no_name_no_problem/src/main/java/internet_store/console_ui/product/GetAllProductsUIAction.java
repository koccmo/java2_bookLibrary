package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.GetAllProductsService;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

@DIComponent
public class GetAllProductsUIAction implements UIAction {

    @DIDependency private GetAllProductsService getAllProductsService;

    @Override
    public void execute(){

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService.execute(getProductsRequest);

        if (getProductsResponse.hasErrors()){
            getProductsResponse.getErrors().forEach(System.out::println);
        }else
            getProductsResponse.getProducts().forEach(System.out::println);
    }

}

