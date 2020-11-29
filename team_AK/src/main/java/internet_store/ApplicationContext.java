package internet_store;

import internet_store.core.service.cart.AddProductToCartService;
import internet_store.core.service.cart.DeleteProductFromCartService;
import internet_store.core.service.cart.PrintCartService;
import internet_store.core.service.cart.UpdateCartService;
import internet_store.core.service.client.*;
import internet_store.core.service.ordering.*;
import internet_store.core.service.product.*;
import internet_store.core.service.telegram.CheckTelegramChatIdService;
import internet_store.core.service.telegram.FindTelegramChatIdService;
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
import internet_store.user_interface.administrator_menu.create_client_menu.ClientMenu;
import internet_store.user_interface.administrator_menu.create_client_menu.ClientMenuConsole;
import internet_store.user_interface.administrator_menu.create_client_menu.delete_client_menu.DeleteClientMenu;
import internet_store.user_interface.administrator_menu.create_client_menu.update_client_menu.UpdateClientMenu;
import internet_store.user_interface.administrator_menu.create_order_menu.OrderMenu;
import internet_store.user_interface.administrator_menu.create_order_menu.OrderMenuConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.delete_order_menu.DeleteOrderConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu.OrderIdMenu;
import internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu.StatusOrderConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu.StatusOrderMenu;
import internet_store.user_interface.administrator_menu.create_product_menu.ProductMenu;
import internet_store.user_interface.administrator_menu.create_product_menu.ProductMenuConsole;
import internet_store.user_interface.administrator_menu.create_product_menu.delete_product_menu.DeleteProductMenu;
import internet_store.user_interface.administrator_menu.create_product_menu.update_product_menu.UpdateProductMenu;
import internet_store.user_interface.client_menu.client_cart_menu.ClientCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.ClientCartMenu;
import internet_store.user_interface.client_menu.client_cart_menu.add_to_cart_menu.AddProductToCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.delete_from_cart_menu.DeleteFromCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.update_cart_menu.UpdateCartConsole;
import internet_store.user_interface.client_menu.ordering_menu.ClientOrderConsole;
import internet_store.user_interface.client_menu.ordering_menu.ClientOrderMenu;
import internet_store.user_interface.main_menu.MainMenu;
import internet_store.user_interface.main_menu.MainMenuConsole;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
    private final Map<Class<?>, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(InnerProductDatabase.class, new InnerProductDatabaseImpl());
        beans.put(AddProductService.class, new AddProductService(getBean(InnerProductDatabase.class)));
        beans.put(DeleteProductService.class, new DeleteProductService(getBean(InnerProductDatabase.class)));
        beans.put(UpdateProductService.class, new UpdateProductService(getBean(InnerProductDatabase.class)));
        beans.put(PrintProductService.class, new PrintProductService(getBean(InnerProductDatabase.class)));
        beans.put(UpdateProductAddNewChangesService.class, new UpdateProductAddNewChangesService(getBean(InnerProductDatabase.class)));

        beans.put(InnerClientDatabase.class, new InnerClientDatabaseImpl());
        beans.put(AddClientService.class, new AddClientService(getBean(InnerClientDatabase.class)));
        beans.put(DeleteClientService.class, new DeleteClientService(getBean(InnerClientDatabase.class)));
        beans.put(UpdateClientService.class, new UpdateClientService(getBean(InnerClientDatabase.class)));
        beans.put(PrintClientService.class, new PrintClientService(getBean(InnerClientDatabase.class)));
        beans.put(UpdateClientAddNewChangesService.class, new UpdateClientAddNewChangesService(getBean(InnerClientDatabase.class)));
        beans.put(InnerCartDatabase.class, new InnerCartDatabaseImpl());
        beans.put(AddProductToCartService.class, new AddProductToCartService(
                getBean(InnerProductDatabase.class),
                getBean(InnerCartDatabase.class)));
        beans.put(PrintCartService.class, new PrintCartService(getBean(InnerCartDatabase.class)));
        beans.put(DeleteProductFromCartService.class, new DeleteProductFromCartService(getBean(InnerCartDatabase.class)));
        beans.put(UpdateCartService.class, new UpdateCartService(
                getBean(InnerProductDatabase.class),
                getBean(InnerCartDatabase.class)));
        beans.put(InnerOrderDatabase.class, new InnerOrderDatabaseImpl());
        beans.put(OrderService.class, new OrderService(
                getBean(InnerClientDatabase.class),
                getBean(InnerCartDatabase.class)));
        beans.put(OrderCreator.class, new OrderCreator(
                getBean(InnerClientDatabase.class),
                getBean(InnerCartDatabase.class),
                getBean(InnerOrderDatabase.class)));
        beans.put(DeleteOrderService.class, new DeleteOrderService(getBean(InnerOrderDatabase.class)));
        beans.put(PrintOrderService.class, new PrintOrderService(getBean(InnerOrderDatabase.class)));
        beans.put(OrderStatusService.class, new OrderStatusService(getBean(InnerOrderDatabase.class)));
        beans.put(CheckOrderService.class, new CheckOrderService(getBean(InnerOrderDatabase.class)));
        beans.put(FindByOrderNumberService.class, new FindByOrderNumberService(getBean(InnerOrderDatabase.class)));
        beans.put(MainMenu.class, new MainMenu());
        beans.put(ProductMenu.class, new ProductMenu());
        beans.put(DeleteProductMenu.class, new DeleteProductMenu());
        beans.put(UpdateProductMenu.class, new UpdateProductMenu());
        beans.put(ClientMenu.class, new ClientMenu());
        beans.put(DeleteClientMenu.class, new DeleteClientMenu());
        beans.put(UpdateClientMenu.class, new UpdateClientMenu());
        beans.put(ClientCartMenu.class, new ClientCartMenu());
        beans.put(ClientOrderMenu.class, new ClientOrderMenu());
        beans.put(StatusOrderMenu.class, new StatusOrderMenu());
        beans.put(OrderIdMenu.class, new OrderIdMenu());
        beans.put(MainMenuConsole.class, new MainMenuConsole());
        beans.put(ProductMenuConsole.class, new ProductMenuConsole(getBean(MainMenuConsole.class)));
        beans.put(ClientMenuConsole.class, new ClientMenuConsole(getBean(MainMenuConsole.class)));
        beans.put(AddProductToCartConsole.class, new AddProductToCartConsole());
        beans.put(DeleteFromCartConsole.class, new DeleteFromCartConsole());
        beans.put(UpdateCartConsole.class, new UpdateCartConsole());
        beans.put(ClientCartConsole.class, new ClientCartConsole(getBean(MainMenuConsole.class)));
        beans.put(ClientOrderConsole.class, new ClientOrderConsole());
        beans.put(OrderMenu.class, new OrderMenu());
        beans.put(OrderMenuConsole.class, new OrderMenuConsole(getBean(MainMenuConsole.class)));
        beans.put(DeleteOrderConsole.class, new DeleteOrderConsole());
        beans.put(StatusOrderConsole.class, new StatusOrderConsole(getBean(OrderMenuConsole.class)));
        beans.put(InitTelegram.class, new InitTelegram());
        beans.put(InnerTelegramDatabase.class, new InnerTelegramDatabaseImpl());
        beans.put(FindTelegramChatIdService.class, new FindTelegramChatIdService(getBean(InnerTelegramDatabase.class)));
        beans.put(CheckTelegramChatIdService.class, new CheckTelegramChatIdService(getBean(InnerTelegramDatabase.class)));
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<?> c) {
        return (T) beans.get(c);
    }
}