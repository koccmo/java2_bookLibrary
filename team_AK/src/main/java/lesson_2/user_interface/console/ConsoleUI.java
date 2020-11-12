package lesson_2.user_interface.console;

import internet_store.domain.Product;
import lesson_1.menu.MainMenu;
import lesson_1.user_handler.UserInputValue;
import lesson_2.ProductListApplication;
import lesson_2.user_interface.console.menu_items_handler.AddProduct;
import lesson_2.user_interface.console.menu_items_handler.DeleteProduct;
import lesson_2.user_interface.console.menu_items_handler.UpdateProduct;

public class ConsoleUI {
    MainMenu mainMenu = new MainMenu();

    public void startConsoleUI() {

        do {
            mainMenu.showMainMenu();
            UserInputValue<?> userInputValue = mainMenu.getUserInputValue();

            switch ((int) userInputValue.getValue()) {
                case 1 -> {
                    Product newProduct = new AddProduct().addTo(new Product());
                    ProductListApplication.addProductService.execute(newProduct);
                }
                case 2 -> {
                    long deletedId = new DeleteProduct().deleteCommandHandler();
                    ProductListApplication.removeProductService.execute(deletedId);
                }
                case 3 -> {
                    long updatedId = new UpdateProduct().updateProductCommand();
                    Product newProduct = new AddProduct().addTo(new Product());
                    ProductListApplication.updateProductService.execute(updatedId, newProduct);
                }
                case 4 -> ProductListApplication.productReportService.execute();
                case 5 -> System.exit(0);
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (true);
    }
}
