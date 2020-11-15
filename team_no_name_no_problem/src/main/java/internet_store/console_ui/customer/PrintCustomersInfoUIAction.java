package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;
import internet_store.core.services.customer.GetAllCustomersService;

public class PrintCustomersInfoUIAction implements UIAction {

    private GetAllCustomersService getAllCustomersService;

    public PrintCustomersInfoUIAction(GetAllCustomersService getAllCustomersService){
        this.getAllCustomersService = getAllCustomersService;
    }

    public void execute(){
        getAllCustomersService.execute();
    }
}
