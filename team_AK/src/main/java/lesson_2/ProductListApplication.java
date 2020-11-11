package lesson_2;

import lesson_2.database.InnerDatabase;
import lesson_2.database.InnerDatabaseImpl;
import lesson_2.service.*;
import lesson_2.user_interface.console.ConsoleUI;

public class ProductListApplication {
    public static InnerDatabase database = new InnerDatabaseImpl();
    public static AddProductService addProductService = new AddProductService(database);
    public static ProductReportService productReportService = new ProductReportService(database);
    public static RemoveProductService removeProductService = new RemoveProductService(database);
    public static UpdateProductService updateProductService = new UpdateProductService(database);
    public static FindProductService findProductService = new FindProductService();

    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.startConsoleUI();
    }
}
