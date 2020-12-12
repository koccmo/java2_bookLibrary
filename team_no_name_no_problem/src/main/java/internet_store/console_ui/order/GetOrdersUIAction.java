package internet_store.console_ui.order;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.order.GetOrdersRequest;
import internet_store.core.response.order.GetOrdersResponse;
import internet_store.core.services.order.GetOrdersService;
import internet_store.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

@Component
public class GetOrdersUIAction implements UIAction {

    @DIDependency private GetOrdersService getOrdersService;

    @Override
    public void execute () {
        System.out.println("Orders: ");

        GetOrdersRequest getOrdersRequest = new GetOrdersRequest();
        GetOrdersResponse getOrdersResponse = getOrdersService.execute(getOrdersRequest);

        if (getOrdersResponse.hasErrors()) {
            getOrdersResponse.getErrors().forEach(System.out::println);
        }else {
            getOrdersResponse.getOrders().forEach(System.out::println);
        }
    }
}
