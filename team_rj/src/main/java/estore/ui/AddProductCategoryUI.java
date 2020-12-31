package estore.ui;

import estore.core.requests.AddNewProductCategoryRequest;
import estore.core.responses.AddNewProductCategoryResponse;
import estore.core.service.AddNewProductCategoryService;
import estore.dependency_injection.DIComponent;
import estore.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class AddProductCategoryUI implements UIAction {

    @DIDependency
    private AddNewProductCategoryService addNewProductCategoryService;

//    public AddProductCategoryUI(AddNewProductCategoryService addNewProductCategoryService) {
//        this.addNewProductCategoryService = addNewProductCategoryService;
//    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter category of the product:");
        String productCategory = sc.nextLine();

        AddNewProductCategoryRequest request = new AddNewProductCategoryRequest(productCategory);
        AddNewProductCategoryResponse response = addNewProductCategoryService.execute(request);

        if (!response.isSuccessfullyAdded()) {
            for (int i = 0; i < response.getErrors().size(); i++) {
                System.out.print("ERROR! ");
                System.out.print(response.getErrors().get(i).getField() + " ");
                System.out.println(response.getErrors().get(i).getMessage());
            }
        } else {
            System.out.println("New product category with id #" + response.getCategory().getId() + " successfully added.");
        }
    }

}
