package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.services.product.AddProductService;

import java.util.Scanner;

public class AddProductUIAction implements UIAction {

    private AddProductService addProductService;

    public AddProductUIAction(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter product's title: ");
        String title = in.nextLine();

        System.out.println("Please enter product's description");
        String description = in.nextLine();

        System.out.println("Please enter product's price");
        Integer price = in.nextInt();

        Product newProduct = new Product(title, description, price);

        AddProductRequest addProductRequest = new AddProductRequest(newProduct);
        AddProductResponse addProductResponse = addProductService.execute(addProductRequest);
        if (addProductResponse.hasErrors()){
            addProductResponse.getErrors().forEach(System.out::println);
        }else {
            System.out.println("Product was successfully added");
        }
    }

}

