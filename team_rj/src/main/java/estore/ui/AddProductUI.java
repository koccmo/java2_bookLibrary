package estore.ui;

import estore.service.AddNewProductService;

public class AddProductUI implements UIAction {

    AddNewProductService addNewProductService;
    private InputValidation iv;

    public AddProductUI(AddNewProductService addNewProductService, InputValidation iv) {
        this.addNewProductService = addNewProductService;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter name of product";
        String productName = iv.getString(description);
        description = "Enter description of product";
        String productDescription = iv.getLine(description);

        boolean successfullyAdded = addNewProductService.execute(productName, productDescription);
        if (successfullyAdded) {
            System.out.println("Product was added to DB.");
        } else {
            System.out.println("Product was not added!");
        }
    }
}
