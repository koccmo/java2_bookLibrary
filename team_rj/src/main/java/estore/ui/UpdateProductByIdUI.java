package estore.ui;

import estore.core.requests.AddNewProductRequest;
import estore.core.requests.GetAllProductCategoriesRequest;
import estore.core.requests.GetAllProductsRequest;
import estore.core.requests.SearchProductByIdRequest;
import estore.core.responses.*;
import estore.core.service.AddNewProductService;
import estore.core.service.GetAllProductCategoriesService;
import estore.core.service.GetAllProductsService;
import estore.core.service.SearchProductByIdService;
import estore.database.ProductDB;
import estore.domain.Product;
import estore.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class UpdateProductByIdUI implements UIAction {

//    private UpdateProductByIdService updateProductByIdService;
    @Autowired
    private GetAllProductsService getAllProductsService;
    @Autowired
    private SearchProductByIdService searchProductByIdService;

//    public UpdateProductByIdUI(UpdateProductByIdService updateProductByIdService) {
//        this.updateProductByIdService = updateProductByIdService;
//    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id of the product");
        String productId = sc.nextLine();

        SearchProductByIdRequest request = new SearchProductByIdRequest(productId);
        SearchProductByIdResponse response = searchProductByIdService.execute(request);

//        List<Product> products = getProducts();
//        Optional<Product> optProductToUpdate = products
//                .stream()
//                .filter(product -> product.getId().toString().equals(productId))
//                .findFirst();
//
//        Product productToUpdate;
//        if (optProductToUpdate.isPresent()) {
//            productToUpdate = optProductToUpdate.get();
//        } else {
//            productToUpdate = null;
//        }
//
//        for (var category : products) {
//            System.out.print(category.getId() + "." + category.getName() + "   ");
//        }
        System.out.println();
        System.out.println("Found product: " + response.getProduct());

//        String productName = sc.nextLine();
//        System.out.println("Enter description of the product");
//        String productDescription = sc.nextLine();
//        System.out.println("Enter category of the product:");

//        List<ProductCategory> categories = getCategories();
//        for (var category : categories) {
//            System.out.print(category.getId() + "." + category.getCategory() + "   ");
//        }
//        System.out.println();
//        System.out.println("Category No: ");
//        String productCategoryNo = sc.nextLine();
//
//        AddNewProductRequest request = new AddNewProductRequest(productName, productDescription, productCategoryNo);
//        AddNewProductResponse response = addNewProductService.execute(request);
//
//        if (!response.isSuccessfullyAdded()) {
//            for (int i = 0; i < response.getErrors().size(); i++) {
//                System.out.print("ERROR! ");
//                System.out.print(response.getErrors().get(i).getField() + " ");
//                System.out.println(response.getErrors().get(i).getMessage());
//            }
//        } else {
//            System.out.println("New product with id #" + response.getProduct().getId() + " successfully added.");
//        }
    }

    private List<Product> getProducts() {
        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse response = getAllProductsService.execute(request);
        return response.getProducts();
    }
}
