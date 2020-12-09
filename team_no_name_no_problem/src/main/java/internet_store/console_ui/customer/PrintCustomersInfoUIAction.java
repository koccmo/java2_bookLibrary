package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.GetAllCustomersService;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

@DIComponent
public class PrintCustomersInfoUIAction implements UIAction {

    @DIDependency private GetAllCustomersService getAllCustomersService;

    @Override
    public void execute(){

        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService.execute(getAllCustomersRequest);

        if (getAllCustomersResponse.hasErrors()) {
            getAllCustomersResponse.getErrors().forEach(System.out::println);
        }else{
            getAllCustomersResponse.getCustomers().forEach(System.out::println);
        }


    }
}
