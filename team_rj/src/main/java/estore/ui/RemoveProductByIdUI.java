package estore.ui;

import estore.service.RemoveProductByIdService;

public class RemoveProductByIdUI implements UIAction {

    //private ProductDataBase database;
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
        int productsRemoved = removeProductByIdService.execute(productToRemoveId);

        if (productsRemoved == 1) {
            System.out.println(productsRemoved + " product removed.");
        } else {
            System.out.println(productsRemoved + " products removed.");
        }
    }
}
