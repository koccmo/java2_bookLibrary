package estore.ui;

import estore.core.requests.AddNewProductRequest;
import estore.core.requests.GetAllProductCategoriesRequest;
import estore.core.responses.AddNewProductResponse;
import estore.core.responses.GetAllProductCategoriesResponse;
import estore.core.service.AddNewProductService;
import estore.core.service.GetAllProductCategoriesService;
import estore.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AddProductUI implements UIAction {

    private AddNewProductService addNewProductService;
    @Autowired
    private GetAllProductCategoriesService getAllProductCategoriesService;

    public AddProductUI(AddNewProductService addNewProductService) {
        this.addNewProductService = addNewProductService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of the product");
        String productName = sc.nextLine();
        System.out.println("Enter description of the product");
        String productDescription = sc.nextLine();
        System.out.println("Enter category of the product:");

        List<ProductCategory> categories = getCategories();
        for (var category : categories) {
            System.out.print(category.getId() + "." + category.getCategory() + "   ");
        }
        System.out.println();
        System.out.println("Category No: ");
        String productCategoryNo = sc.nextLine();

        AddNewProductRequest request = new AddNewProductRequest(productName, productDescription, productCategoryNo);
        AddNewProductResponse response = addNewProductService.execute(request);

        if (!response.isSuccessfullyAdded()) {
            for (int i = 0; i < response.getErrors().size(); i++) {
                System.out.print("ERROR! ");
                System.out.print(response.getErrors().get(i).getField() + " ");
                System.out.println(response.getErrors().get(i).getMessage());
            }
        } else {
            System.out.println("New product with id #" + response.getProduct().getId() + " successfully added.");
        }
    }

    private List<ProductCategory> getCategories() {
        GetAllProductCategoriesRequest request = new GetAllProductCategoriesRequest();
        GetAllProductCategoriesResponse response = getAllProductCategoriesService.execute(request);
        return response.getCategories();
    }
}
