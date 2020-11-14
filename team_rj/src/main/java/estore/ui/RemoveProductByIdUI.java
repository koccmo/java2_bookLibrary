package estore.ui;

import estore.core.requests.RemoveProductByIdRequest;
import estore.core.responses.RemoveProductByIdResponse;
import estore.core.service.RemoveProductByIdService;

public class RemoveProductByIdUI implements UIAction {

    private RemoveProductByIdService removeProductByIdService;
    private InputValidation iv;

    public RemoveProductByIdUI(RemoveProductByIdService removeProductByIdService, InputValidation iv) {
        this.removeProductByIdService = removeProductByIdService;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter id of product to remove: ";
        int productToRemoveId = iv.getPositiveInteger(description);

        RemoveProductByIdRequest request = new RemoveProductByIdRequest(Long.valueOf(productToRemoveId));
        RemoveProductByIdResponse response = removeProductByIdService.execute(request);

        if (response.getProductsRemoved() == 1) {
            System.out.println(response.getProductsRemoved() + " product removed.");
        } else {
            System.out.println(response.getProductsRemoved() + " products removed.");
        }
    }
}
