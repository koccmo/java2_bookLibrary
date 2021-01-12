package lv.estore.app;

import lv.estore.app.config.EStoreConfiguration;
import lv.estore.app.userInterface.AddUIAction;
import lv.estore.app.userInterface.ExitUIAction;
import lv.estore.app.userInterface.FindByIdUIAction;
import lv.estore.app.userInterface.FindByNameUIAction;
import lv.estore.app.userInterface.GetAllUIAction;
import lv.estore.app.userInterface.MenuUI;
import lv.estore.app.userInterface.RemoveByIdUIAction;
import lv.estore.app.userInterface.RemoveByNameUIAction;
import lv.estore.app.userInterface.SearchUIAction;
import lv.estore.app.userInterface.UpdateUIAction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class EStoreApplication {

    private static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(EStoreConfiguration.class);

    public static void main(String[] args) {
        while (true) {
            printProgramMenu();
            Integer menuNumber = getMenuNumberFromUser();
            if (menuNumber == null) {
                continue;
            }
            executeSelectedMenuItem(menuNumber);
        }
    }

    private static void printProgramMenu() {
        MenuUI menuUI = applicationContext.getBean(MenuUI.class);
        menuUI.execute();
    }

    private static Integer getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        int result;
        try{
            result = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid option");
            return null;
        }
        return result;
    }

    private static void executeSelectedMenuItem(int selectedMenu) {
        switch (selectedMenu) {
            case 1: {
                AddUIAction uiAction = applicationContext.getBean(AddUIAction.class);
                uiAction.execute();
                break;
            }
            case 2: {
                GetAllUIAction uiAction = applicationContext.getBean(GetAllUIAction.class);
                uiAction.execute();
                break;
            }
            case 3: {
                FindByIdUIAction uiAction = applicationContext.getBean(FindByIdUIAction.class);
                uiAction.execute();
                break;
            }
            case 4: {
                FindByNameUIAction uiAction = applicationContext.getBean(FindByNameUIAction.class);
                uiAction.execute();
                break;
            }
            case 5: {
                RemoveByIdUIAction uiAction = applicationContext.getBean(RemoveByIdUIAction.class);
                uiAction.execute();
                break;
            }
            case 6: {
                RemoveByNameUIAction uiAction = applicationContext.getBean(RemoveByNameUIAction.class);
                uiAction.execute();
                break;
            }
            case 7: {
                UpdateUIAction uiAction = applicationContext.getBean(UpdateUIAction.class);
                uiAction.execute();
                break;
            }
            case 8: {
                SearchUIAction uiAction = applicationContext.getBean(SearchUIAction.class);
                uiAction.execute();
                break;
            }
            case 9: {
                ExitUIAction uiAction = applicationContext.getBean(ExitUIAction.class);
                uiAction.execute();
                break;
            }
        }
    }
}