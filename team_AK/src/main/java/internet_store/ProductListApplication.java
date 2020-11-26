package internet_store;

import internet_store.core.service.add_client.AddClientService;
import internet_store.core.service.add_client.UpdateClientAddNewChangesService;
import internet_store.core.service.add_product.AddProductService;
import internet_store.core.service.add_product.UpdateProductAddNewChangesService;
import internet_store.core.service.add_product_to_cart.AddProductToCartService;
import internet_store.core.service.check_order_id.CheckOrderService;
import internet_store.core.service.delete_client.DeleteClientService;
import internet_store.core.service.delete_from_cart.DeleteProductFromCartService;
import internet_store.core.service.delete_order.DeleteOrderService;
import internet_store.core.service.delete_product.DeleteProductService;
import internet_store.core.service.find_by_order_number.FindByOrderNumberService;
import internet_store.core.service.order_status_update.OrderStatusService;
import internet_store.core.service.ordering.OrderService;
import internet_store.core.service.print_client_service.PrintClientService;
import internet_store.core.service.print_order.PrintOrderService;
import internet_store.core.service.print_product_service.PrintProductService;
import internet_store.core.service.print_products_from_cart.PrintCartService;
import internet_store.core.service.telegram.check_telegram_chatid_service.CheckTelegramChatIdService;
import internet_store.core.service.telegram.find_telegram_chatid_service.FindTelegramChatIdService;
import internet_store.core.service.update_cart_service.UpdateCartService;
import internet_store.core.service.update_client_service.UpdateClientService;
import internet_store.core.service.update_product_service.UpdateProductService;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.cart_database.InnerCartDatabaseImpl;
import internet_store.database.client_database.InnerClientDatabase;
import internet_store.database.client_database.InnerClientDatabaseImpl;
import internet_store.database.order_database.InnerOrderDatabase;
import internet_store.database.order_database.InnerOrderDatabaseImpl;
import internet_store.database.product_database.InnerProductDatabase;
import internet_store.database.product_database.InnerProductDatabaseImpl;
import internet_store.database.telegram_database.InnerTelegramDatabase;
import internet_store.database.telegram_database.InnerTelegramDatabaseImpl;
import internet_store.integration.telegram.InitTelegram;
import internet_store.ordering.OrderCreator;
import internet_store.user_interface.administrator_menu.create_client_menu.ClientMenuConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.OrderMenuConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.delete_order_menu.DeleteOrderConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu.StatusOrderConsole;
import internet_store.user_interface.administrator_menu.create_product_menu.ProductMenuConsole;
import internet_store.user_interface.client_menu.client_cart_menu.ClientCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.add_to_cart_menu.AddProductToCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.delete_from_cart_menu.DeleteFromCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.update_cart_menu.UpdateCartConsole;
import internet_store.user_interface.client_menu.ordering_menu.ClientOrderConsole;
import internet_store.user_interface.main_menu.MainMenuConsole;

public class ProductListApplication {
    public static InnerProductDatabase productDatabase = new InnerProductDatabaseImpl();
    public static AddProductService addProductService = new AddProductService(productDatabase);
    public static DeleteProductService deleteProductService = new DeleteProductService(productDatabase);
    public static UpdateProductService updateProductService = new UpdateProductService(productDatabase);
    public static PrintProductService printProductService = new PrintProductService(productDatabase);
    public static UpdateProductAddNewChangesService updateProductServiceNewChanges = new UpdateProductAddNewChangesService(productDatabase);

    public static InnerClientDatabase clientDatabase = new InnerClientDatabaseImpl();
    public static AddClientService addClientService = new AddClientService(clientDatabase);
    public static DeleteClientService deleteClientService = new DeleteClientService(clientDatabase);
    public static UpdateClientService updateClientService = new UpdateClientService(clientDatabase);
    public static PrintClientService printClientService = new PrintClientService(clientDatabase);
    public static UpdateClientAddNewChangesService updateClientAddNewChangesService = new UpdateClientAddNewChangesService(clientDatabase);

    public static InnerCartDatabase cartDatabase = new InnerCartDatabaseImpl();
    public static AddProductToCartService addToCartService = new AddProductToCartService(productDatabase, cartDatabase);
    public static PrintCartService printCartService = new PrintCartService(cartDatabase);
    public static DeleteProductFromCartService deleteProductFromCartService = new DeleteProductFromCartService(cartDatabase);
    public static UpdateCartService updateCartService = new UpdateCartService(productDatabase, cartDatabase);

    public static InnerOrderDatabase orderDatabase = new InnerOrderDatabaseImpl();
    public static OrderService orderService = new OrderService(clientDatabase, cartDatabase);
    public static OrderCreator orderCreator = new OrderCreator(clientDatabase, cartDatabase, orderDatabase);
    public static DeleteOrderService deleteOrderService = new DeleteOrderService(orderDatabase);
    public static PrintOrderService printOrderService = new PrintOrderService(orderDatabase);
    public static OrderStatusService orderStatusService = new OrderStatusService(orderDatabase);
    public static CheckOrderService checkOrderService = new CheckOrderService(orderDatabase);
    public static FindByOrderNumberService findByOrderNumberService = new FindByOrderNumberService(orderDatabase);

    public static MainMenuConsole mainMenuConsole = new MainMenuConsole();
    public static ProductMenuConsole productMenuConsole = new ProductMenuConsole(mainMenuConsole);
    public static ClientMenuConsole clientMenuConsole = new ClientMenuConsole(mainMenuConsole);
    public static AddProductToCartConsole addToCartConsole = new AddProductToCartConsole();
    public static DeleteFromCartConsole deleteFromCartConsole = new DeleteFromCartConsole();
    public static UpdateCartConsole updateCartConsole = new UpdateCartConsole();
    public static ClientCartConsole clientCartConsole = new ClientCartConsole(mainMenuConsole);
    public static ClientOrderConsole clientOrderConsole = new ClientOrderConsole();
    public static OrderMenuConsole orderMenuConsole = new OrderMenuConsole(mainMenuConsole);
    public static DeleteOrderConsole deleteOrderConsole = new DeleteOrderConsole();
    public static StatusOrderConsole statusOrderConsole = new StatusOrderConsole(orderMenuConsole);

    public static InitTelegram initTelegram = new InitTelegram();
    public static InnerTelegramDatabase telegramDatabase = new InnerTelegramDatabaseImpl();
    public static FindTelegramChatIdService telegramChatIdService = new FindTelegramChatIdService(telegramDatabase);
    public static CheckTelegramChatIdService checkTelegramChatIdService = new CheckTelegramChatIdService(telegramDatabase);

    public static void main(String[] args) {
        initTelegram.init();
        mainMenuConsole.startMainMenu();
    }
}