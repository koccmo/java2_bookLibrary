package internet_store.UI;

import internet_store.services.GetAllProductsService;

class GetAllProductsUIAction implements UIAction {

    private GetAllProductsService getAllProductsService;

    public GetAllProductsUIAction(GetAllProductsService getAllProductsService) {
        this.getAllProductsService = getAllProductsService;
    }

    public void execute(){
        getAllProductsService.execute();
    }

}

