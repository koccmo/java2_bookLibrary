package estore.ui;

import estore.database.ProductDataBase;
import estore.requests.RemoveProductByNameRequest;
import estore.responses.RemoveProductByNameResponse;
import estore.service.RemoveProductByNameService;

public class RemoveProductByNameUI implements UIAction {

    private RemoveProductByNameService removeProductByNameService;
    private InputValidation iv;

    public RemoveProductByNameUI(RemoveProductByNameService removeProductByNameService, InputValidation iv) {
        this.removeProductByNameService = removeProductByNameService;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter name of product to remove: ";
        String productToRemove = iv.getString(description);

        RemoveProductByNameRequest request = new RemoveProductByNameRequest(productToRemove);
        RemoveProductByNameResponse response = removeProductByNameService.execute(request);

        if (response.getProductsRemoved() == 1) {
            System.out.println(response.getProductsRemoved() + " product removed.");
        } else {
            System.out.println(response.getProductsRemoved() + " products removed");
        }
    }
}
