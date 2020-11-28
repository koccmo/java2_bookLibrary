package internet_store.application;

import internet_store.application.core.services.*;
import internet_store.application.console_ui.*;
import java.util.*;

class ProductList {

    private static final ApplicationContext applicationContext = new ApplicationContext();
    private final Map<Integer, UIAction> menuNumberToActionMap;

    public ProductList() {

        menuNumberToActionMap = new HashMap<>();
        menuNumberToActionMap.put(1, new AddProductUIAction(applicationContext.getBean(AddProductService.class)));
        menuNumberToActionMap.put(2, new DeleteByIdUIAction(applicationContext.getBean(DeleteByProductIdService.class)));
        menuNumberToActionMap.put(3, new DeleteByProductUIAction(applicationContext.getBean(DeleteProductByProductService.class)));
        menuNumberToActionMap.put(4, new DeleteByProductNameUIAction(applicationContext.getBean(DeleteProductByNameService.class)));
        menuNumberToActionMap.put(5, new FindByIdUIAction(applicationContext.getBean(FindByIdService.class)));
        menuNumberToActionMap.put(6, new FindProductsUIAction(applicationContext.getBean(FindProductsService.class)));
        menuNumberToActionMap.put(7, new ChangeProductNameUIAction(applicationContext.getBean(ChangeProductNameService.class), applicationContext.getBean(FindByIdService.class)));
        menuNumberToActionMap.put(8, new PrintProductsToConsoleUIAction(applicationContext.getBean(GetProductListService.class)));
        menuNumberToActionMap.put(0, new ExitProgramUIAction());
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printOutMenu();
            try {
                int userSelectedMenuNumber = Integer.parseInt(sc.nextLine());
                executeUIAction(userSelectedMenuNumber);
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect input, please enter number");
            }
        }
    }

    private void printOutMenu() {
        System.out.println("\n1. Add product to database");
        System.out.println("2. Delete product from database by ID");
        System.out.println("3. Delete product from database by name and description");
        System.out.println("4. Delete product from database by name");
        System.out.println("5. Find product(s) from database by ID");
        System.out.println("6. Find product(s) from database by name and(or) description");
        System.out.println("7. Find product(s) from database by ID and change name");
        System.out.println("8. Print out all database products");
        System.out.println("0. Exit the program");
        System.out.println("------------------------------------------------------------");
        System.out.print("Please enter menu number: ");
    }

    private void executeUIAction(int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToActionMap.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item doesn't exist: " + userSelectedMenuNumber);
        }
    }

}
