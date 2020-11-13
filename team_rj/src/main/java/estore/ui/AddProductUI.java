package estore.ui;

import estore.requests.AddNewProductRequest;
import estore.responses.AddNewProductResponse;
import estore.service.AddNewProductService;

public class AddProductUI implements UIAction {

    private AddNewProductService addNewProductService;
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

        AddNewProductRequest request = new AddNewProductRequest(productName, productDescription);
        AddNewProductResponse response = addNewProductService.execute(request);

        if (response.isSuccessfullyAdded()) {
            System.out.println("New product with id #" + response.getProduct().getId() + " successfully added.");
        } else {
            System.out.println("Failed to add new product");
        }
    }
}
