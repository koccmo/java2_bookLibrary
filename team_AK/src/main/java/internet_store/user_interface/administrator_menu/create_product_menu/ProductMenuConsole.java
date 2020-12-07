package internet_store.user_interface.administrator_menu.create_product_menu;

import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.request.product.UpdateProductRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.response.product.UpdateProductResponse;
import internet_store.core.service.product.*;
import internet_store.user_interface.administrator_menu.create_product_menu.add_product_menu.AddProduct;
import internet_store.user_interface.administrator_menu.create_product_menu.delete_product_menu.DeleteProductMenu;
import internet_store.user_interface.administrator_menu.create_product_menu.update_product_menu.UpdateProductMenu;
import internet_store.user_interface.main_menu.MainMenuConsole;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

@DIComponent
public class ProductMenuConsole {
    @DIDependency
    MainMenuConsole mainMenuConsole;
    @DIDependency
    ProductMenu productMenu;
    @DIDependency
    DeleteProductMenu deleteProductMenu;
    @DIDependency
    UpdateProductMenu updateProductMenu;
    @DIDependency
    AddProductService addProductService;
    @DIDependency
    DeleteProductService deleteProductService;
    @DIDependency
    UpdateProductService updateProductService;
    @DIDependency
    UpdateProductAddNewChangesService updateProductServiceNewChanges;
    @DIDependency
    PrintProductService printProductService;

    public void startProductMenuConsole() {
        boolean returnMainMenu = true;
        do {
            productMenu.showMainMenu();
            int userInput = productMenu.getUserInput();
            switch (userInput) {
                case 1 -> {
                    Product product = new AddProduct().addProduct();
                    AddProductRequest productRequest = new AddProductRequest(product);
                    AddProductResponse response = addProductService
                            .execute(productRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Product add to list");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }
                }
                case 2 -> {
                    deleteProductMenu.showMenuDeleteProduct();
                    long deletedId = deleteProductMenu.getUserDeletedProductIdInput();
                    DeleteProductRequest deleteRequest = new DeleteProductRequest(deletedId);
                    DeleteProductResponse response = deleteProductService
                            .execute(deleteRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Product deleted from list");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }
                }
                case 3 -> {
                    updateProductMenu.showMenuUpdateProduct();
                    long updatedId = updateProductMenu.getUserUpdatedProductIdInput();

                    UpdateProductRequest updateRequest = new UpdateProductRequest(updatedId);
                    UpdateProductResponse updateResponse = updateProductService
                            .execute(updateRequest);

                    if (updateResponse.hasErrors()) {
                        updateResponse.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                        break;
                    }

                    Product product = new AddProduct().addProduct();
                    AddProductRequest productRequest = new AddProductRequest(product);
                    productRequest.getProduct().setId(updatedId);
                    AddProductResponse response = updateProductServiceNewChanges
                            .execute(productRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Product updated");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }
                }
                case 4 -> printProductService.print();
                case 5 -> returnMainMenu = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (returnMainMenu);
        mainMenuConsole.startMainMenu();
    }
}