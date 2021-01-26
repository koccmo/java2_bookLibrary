package estore.ui;

import estore.config.ProductConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserAction {

    private static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(ProductConfiguration.class);

    public void run() {
        UserMenu userMenu = new UserMenu();
        printGreeting();
        while (true) {
            System.out.println("");
            System.out.println("Choose option by typing a valid number");
            userMenu.printUserMenu();
            UserMenuChoiceValidation menuChoiceValidation = applicationContext.getBean(UserMenuChoiceValidation.class);
            int userInput = menuChoiceValidation.getUserInputOfMenuItem(userMenu.getUserMenuSize());
            executeMenuItem(userInput);
        }
    }

    private void printGreeting() {
        System.out.println("Welcome to RedDots!");
    }

    private void executeMenuItem(int menuItem) {
        switch (menuItem) {
            case 1: {
                ShowAllProductsUI ui = applicationContext.getBean(ShowAllProductsUI.class);
                ui.execute();
                break;
            }
            case 2: {
                SearchProductByNameUI ui = applicationContext.getBean(SearchProductByNameUI.class);
                ui.execute();
                break;
            }
            case 3: {
                SearchProductByCategoryUI ui = applicationContext.getBean(SearchProductByCategoryUI.class);
                ui.execute();
                break;
            }
            case 4: {
                AddProductUI ui = applicationContext.getBean(AddProductUI.class);
                ui.execute();
                break;
            }
            case 5: {
                AddProductCategoryUI ui = applicationContext.getBean(AddProductCategoryUI.class);
                ui.execute();
                break;
            }
            case 6: {
                RemoveProductByNameUI ui = applicationContext.getBean(RemoveProductByNameUI.class);
                ui.execute();
                break;
            }
            case 7: {
                RemoveProductByIdUI ui = applicationContext.getBean(RemoveProductByIdUI.class);
                ui.execute();
                break;
            }
            case 8: {
                UpdateProductByIdUI ui = applicationContext.getBean(UpdateProductByIdUI.class);
                ui.execute();
                break;
            }
            case 0: {
                ExitProgramUI ui = applicationContext.getBean(ExitProgramUI.class);
                ui.execute();
                break;
            }
        }
    }

}
