package lesson_3;

import lesson_3.core.service.add_client.AddClientService;
import lesson_3.core.service.add_client.UpdateClientAddNewChangesService;
import lesson_3.core.service.add_product.AddProductService;
import lesson_3.core.service.add_product.UpdateProductAddNewChangesService;
import lesson_3.core.service.delete_client.DeleteClientService;
import lesson_3.core.service.delete_product.DeleteProductService;
import lesson_3.core.service.find_client_service.FindClientService;
import lesson_3.core.service.find_product_service.FindProductService;
import lesson_3.core.service.print_client_service.PrintClientService;
import lesson_3.core.service.print_product_service.PrintProductService;
import lesson_3.core.service.update_client_service.UpdateClientService;
import lesson_3.core.service.update_product_service.UpdateProductService;
import lesson_3.database.client_database.InnerClientDatabase;
import lesson_3.database.client_database.InnerClientDatabaseImpl;
import lesson_3.database.product_database.InnerProductDatabase;
import lesson_3.database.product_database.InnerProductDatabaseImpl;
import lesson_3.user_interface.client_menu.ClientMenuConsole;
import lesson_3.user_interface.main_menu.MainMenuConsole;
import lesson_3.user_interface.product_menu.ProductMenuConsole;

public class ProductListApplication {
    public static InnerProductDatabase productDatabase = new InnerProductDatabaseImpl();
    public static AddProductService addProductService = new AddProductService(productDatabase);
    public static DeleteProductService deleteProductService = new DeleteProductService(productDatabase);
    public static UpdateProductService updateProductService = new UpdateProductService(productDatabase);
    public static PrintProductService printProductService = new PrintProductService(productDatabase);
    public static UpdateProductAddNewChangesService updateProductServiceNewChanges = new UpdateProductAddNewChangesService(productDatabase);
    public static FindProductService findProductService = new FindProductService();

    public static InnerClientDatabase clientDatabase = new InnerClientDatabaseImpl();
    public static AddClientService addClientService = new AddClientService(clientDatabase);
    public static DeleteClientService deleteClientService = new DeleteClientService(clientDatabase);
    public static UpdateClientService updateClientService = new UpdateClientService(clientDatabase);
    public static PrintClientService printClientService = new PrintClientService(clientDatabase);
    public static UpdateClientAddNewChangesService updateClientAddNewChangesService = new UpdateClientAddNewChangesService(clientDatabase);
    public static FindClientService findClientService = new FindClientService();

    public static MainMenuConsole mainMenuConsole = new MainMenuConsole();
    public static ProductMenuConsole productMenuConsole = new ProductMenuConsole(mainMenuConsole);
    public static ClientMenuConsole clientMenuConsole = new ClientMenuConsole(mainMenuConsole);

    public static void main(String[] args) {
        mainMenuConsole.startMainMenu();
    }
}