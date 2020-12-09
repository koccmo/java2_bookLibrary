package internet_store.application;

import internet_store.application.console_ui.*;
import internet_store.application.dependency_injection.ApplicationContext;
import internet_store.application.dependency_injection.DIApplicationContextBuilder;

import java.util.*;

class ProductList {

    private static ApplicationContext applicationContext
            = new DIApplicationContextBuilder().build("internet_store.application");
    private Map<Integer, UIAction> menuNumberToActionMap;

    public ProductList() {

        menuNumberToActionMap = new HashMap<>();

        menuNumberToActionMap.put(1, applicationContext.getBean(AddProductUIAction.class));
        menuNumberToActionMap.put(2, applicationContext.getBean(DeleteByIdUIAction.class));
        menuNumberToActionMap.put(3, applicationContext.getBean(DeleteByProductUIAction.class));
        menuNumberToActionMap.put(4, applicationContext.getBean(DeleteByProductNameUIAction.class));
        menuNumberToActionMap.put(5, applicationContext.getBean(FindByIdUIAction.class));
        menuNumberToActionMap.put(6, applicationContext.getBean(FindProductsUIAction.class));
        menuNumberToActionMap.put(7, applicationContext.getBean(ChangeProductNameUIAction.class));
        menuNumberToActionMap.put(8, applicationContext.getBean(GetAllProductsUIAction.class));
        menuNumberToActionMap.put(0, applicationContext.getBean(ExitProgramUIAction.class));
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
