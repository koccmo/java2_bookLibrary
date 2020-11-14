package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.services.product.GetAllProductsService;

public class GetAllProductsUIAction implements UIAction {

    private GetAllProductsService getAllProductsService;

    public GetAllProductsUIAction(GetAllProductsService getAllProductsService) {
        this.getAllProductsService = getAllProductsService;
    }

    public void execute(){
        if (getAllProductsService.execute().isEmpty()){
            System.out.println("Database is empty");
        }else
            getAllProductsService.execute().forEach(System.out::println);
    }

}

