package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.GetAllCustomersService;

public class PrintCustomersInfoUIAction implements UIAction {

    private GetAllCustomersService getAllCustomersService;

    public PrintCustomersInfoUIAction(GetAllCustomersService getAllCustomersService){
        this.getAllCustomersService = getAllCustomersService;
    }

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
