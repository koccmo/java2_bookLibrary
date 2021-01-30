package estore.ui;

import estore.core.requests.AddProductCategoryRequest;
import estore.core.responses.AddProductCategoryResponse;
import estore.core.service.AddProductCategoryService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddProductCategoryUI implements UIAction {

    private AddProductCategoryService addProductCategoryService;

    public AddProductCategoryUI(AddProductCategoryService addProductCategoryService) {
        this.addProductCategoryService = addProductCategoryService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter category of the product:");
        String productCategory = sc.nextLine();

        AddProductCategoryRequest request = new AddProductCategoryRequest(productCategory);
        AddProductCategoryResponse response = addProductCategoryService.execute(request);

        if (response.hasErrors()) {
            for (int i = 0; i < response.getErrors().size(); i++) {
                System.out.print("ERROR! ");
                System.out.print(response.getErrors().get(i).getField() + " ");
                System.out.println(response.getErrors().get(i).getMessage());
            }
        } else {
            System.out.println("New product category " + response.getCategory() +
                    " with id #" + response.getCategory().getId() + " successfully added.");
        }
    }

}
