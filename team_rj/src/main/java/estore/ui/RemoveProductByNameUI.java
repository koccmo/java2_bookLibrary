package estore.ui;

import estore.core.requests.RemoveProductByNameRequest;
import estore.core.responses.RemoveProductByNameResponse;
import estore.core.service.RemoveProductByNameService;

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
