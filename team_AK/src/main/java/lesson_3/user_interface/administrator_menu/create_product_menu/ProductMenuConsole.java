package lesson_3.user_interface.administrator_menu.create_product_menu;

import lesson_3.ProductListApplication;
import lesson_3.core.domain.Product;
import lesson_3.core.request.add_product.AddProductRequest;
import lesson_3.core.request.delete_product.DeleteProductRequest;
import lesson_3.core.request.update_product.UpdateProductRequest;
import lesson_3.core.response.add_product.AddProductResponse;
import lesson_3.core.response.delete_product.DeleteProductResponse;
import lesson_3.core.response.update_product.UpdateProductResponse;
import lesson_3.user_interface.main_menu.MainMenuConsole;
import lesson_3.user_interface.administrator_menu.create_product_menu.add_product_menu.AddProduct;
import lesson_3.user_interface.administrator_menu.create_product_menu.delete_product_menu.DeleteProductMenu;
import lesson_3.user_interface.administrator_menu.create_product_menu.update_product_menu.UpdateProductMenu;

public class ProductMenuConsole {

    private final ProductMenu productMenu = new ProductMenu();
    private final DeleteProductMenu deleteMenu = new DeleteProductMenu();
    private final UpdateProductMenu updateMenu = new UpdateProductMenu();
    private final MainMenuConsole mainMenuConsole;

    public ProductMenuConsole(MainMenuConsole mainMenuConsole) {
        this.mainMenuConsole = mainMenuConsole;
    }

    public void startProductMenuConsole() {
        boolean returnMainMenu = true;
        do {
            productMenu.showMainMenu();
            int userInput = productMenu.getUserInput();
            switch (userInput) {
                case 1 -> {
                    Product product = new AddProduct().addProduct();
                    AddProductRequest productRequest = new AddProductRequest(product);
                    AddProductResponse response = ProductListApplication.addProductService
                            .execute(productRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Product add to list");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }
                }
                case 2 -> {
                    deleteMenu.showMenuDeleteProduct();
                    long deletedId = deleteMenu.getUserDeletedProductIdInput();
                    DeleteProductRequest deleteRequest = new DeleteProductRequest(deletedId);
                    DeleteProductResponse response = ProductListApplication.deleteProductService
                            .execute(deleteRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Product deleted from list");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }
                }
                case 3 -> {
                    updateMenu.showMenuUpdateProduct();
                    long updatedId = updateMenu.getUserUpdatedProductIdInput();

                    UpdateProductRequest updateRequest = new UpdateProductRequest(updatedId);
                    UpdateProductResponse updateResponse = ProductListApplication.updateProductService
                            .execute(updateRequest);

                    if (updateResponse.hasErrors()) {
                        updateResponse.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                        break;
                    }

                    Product product = new AddProduct().addProduct();
                    AddProductRequest productRequest = new AddProductRequest(product);
                    productRequest.getProduct().setId(updatedId);
                    AddProductResponse response = ProductListApplication.updateProductServiceNewChanges
                            .execute(productRequest);
                    if (!(response.hasErrors())) {
                        System.out.println("Product updated");
                    } else {
                        response.getErrors().forEach(r -> System.out.println(r.getField() +
                                r.getMessage()));
                    }
                }
                case 4 -> ProductListApplication.printProductService.print();
                case 5 -> returnMainMenu = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (returnMainMenu);
        mainMenuConsole.startMainMenu();
    }
}