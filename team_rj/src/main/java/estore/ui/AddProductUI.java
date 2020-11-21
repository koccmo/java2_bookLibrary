package estore.ui;

import estore.core.requests.AddNewProductRequest;
import estore.core.responses.AddNewProductResponse;
import estore.core.service.AddNewProductService;

import java.util.Scanner;

public class AddProductUI implements UIAction {

    private AddNewProductService addNewProductService;

    public AddProductUI(AddNewProductService addNewProductService) {
        this.addNewProductService = addNewProductService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of product");
        String productName = sc.nextLine();
        System.out.println("Enter description of product");
        String productDescription = sc.nextLine();

        AddNewProductRequest request = new AddNewProductRequest(productName, productDescription);
        AddNewProductResponse response = addNewProductService.execute(request);

        if (!response.isSuccessfullyAdded()) {
            for (int i = 0; i < response.getErrors().size(); i++) {
                System.out.print(response.getErrors().get(i).getField() + " ");
                System.out.println(response.getErrors().get(i).getMessage());
            }
        } else {
            System.out.println("New product with id #" + response.getProduct().getId() + " successfully added.");
        }
    }
}
