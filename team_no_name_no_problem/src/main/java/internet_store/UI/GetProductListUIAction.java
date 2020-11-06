package internet_store.UI;

import internet_store.database.ProductDatabase;
import internet_store.services.GetProductListService;

class GetProductListUIAction implements UIAction {

    private GetProductListService getProductListService;

    public GetProductListUIAction(GetProductListService getProductListService) {
        this.getProductListService = getProductListService;
    }

    public void execute(){
        getProductListService.execute();
    }

}

