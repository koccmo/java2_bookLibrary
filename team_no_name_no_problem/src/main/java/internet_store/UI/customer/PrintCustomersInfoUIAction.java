package internet_store.UI.customer;

import internet_store.UI.UIAction;
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
