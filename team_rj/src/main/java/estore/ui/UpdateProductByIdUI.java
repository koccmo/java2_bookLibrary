package estore.ui;

import estore.core.requests.SearchProductByIdRequest;
import estore.core.requests.UpdateProductByIdRequest;
import estore.core.responses.SearchProductByIdResponse;
import estore.core.responses.UpdateProductByIdResponse;
import estore.core.service.SearchProductByIdService;
import estore.core.service.UpdateProductByIdService;
import estore.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UpdateProductByIdUI implements UIAction {

    @Autowired
    private SearchProductByIdService searchProductByIdService;
    @Autowired
    private UpdateProductByIdService updateProductByIdService;

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of the product");
        String productId = sc.nextLine();

        SearchProductByIdRequest searchProductByIdRequest = new SearchProductByIdRequest(productId);
        SearchProductByIdResponse searchProductByIdResponse = searchProductByIdService.execute(searchProductByIdRequest);

        if (searchProductByIdResponse.hasErrors()) {
            for (int i = 0; i < searchProductByIdResponse.getErrors().size(); i++) {
                System.out.print("ERROR! ");
                System.out.print(searchProductByIdResponse.getErrors().get(i).getField() + " ");
                System.out.println(searchProductByIdResponse.getErrors().get(i).getMessage());
            }
            return;
        }

        if (searchProductByIdResponse.getProductsFound() == 0) {
            System.out.println("No products found");
            return;
        }

        Product productToUpdate = searchProductByIdResponse.getProduct();
        System.out.println("Found product: " + productToUpdate);
        UpdateProductByIdRequest request = makeRequest(productToUpdate, sc);

        UpdateProductByIdResponse response = updateProductByIdService.execute(request);
        if (response.hasErrors()) {
            for (int i = 0; i < response.getErrors().size(); i++) {
                System.out.print("ERROR! ");
                System.out.print(response.getErrors().get(i).getField() + " ");
                System.out.println(response.getErrors().get(i).getMessage());
            }
            return;
        } else{
            System.out.println("Successfully updated!");
            System.out.println(response.getProduct());
        }
    }

    private UpdateProductByIdRequest makeRequest(Product productToUpdate, Scanner sc) {
        System.out.println("Enter updated name of the product, enter to skip:");
        String productName = sc.nextLine();
        if (productName == "") {
            productName = productToUpdate.getName();
        }
        System.out.println("Enter updated description of the product, enter to skip:");
        String productDescription = sc.nextLine();
        if (productDescription == "") {
            productDescription = productToUpdate.getDescription();
        }
        System.out.println("Enter updated category of the product, enter to skip:");
        String productCategory = sc.nextLine();
        if (productCategory == "") {
            productCategory = productToUpdate.getCategory().toString();
        }
        System.out.println("Enter updated quantity of the product, enter to skip:");
        String productQuantity= sc.nextLine();
        if (productQuantity == "") {
            productQuantity = productToUpdate.getQuantity() + "";
        }
        System.out.println("Enter updated price of the product, enter to skip:");
        String productPrice= sc.nextLine();
        if (productPrice == "") {
            productPrice = productToUpdate.getPrice() + "";
        }
        UpdateProductByIdRequest request = new UpdateProductByIdRequest(
                productToUpdate.getId(),
                productName,
                productDescription,
                productCategory,
                productQuantity,
                productPrice);
        return request;
    }
}
