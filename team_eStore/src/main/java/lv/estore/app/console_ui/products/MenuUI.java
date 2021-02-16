package lv.estore.app.console_ui.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class MenuUI {

    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public MenuUI(List<UIAction> uiActionList){
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActionList, AddUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActionList, GetAllUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActionList, FindByIdUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActionList, FindByNameUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActionList, RemoveByIdUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActionList, RemoveByNameUIAction.class));
        menuNumberToUIActionMap.put(7, findUIAction(uiActionList, UpdateByIdUIAction.class));
        menuNumberToUIActionMap.put(8, findUIAction(uiActionList, ExitUIAction.class));
    }

    private UIAction findUIAction(List<UIAction> uiActionList, Class uiActionClass){
        return uiActionList.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    /**
     * Method ot show menu
     */
    public void showMenu(){
        System.out.println("____Welcome to eStore____");
        System.out.println("Choose option by typing a valid number. Type \"exit\" to quit program");
        System.out.println("1 - add product to the store");
        System.out.println("2 - show all products");
        System.out.println("3 - find product by 'Id'");
        System.out.println("4 - find product by 'Name'");
        System.out.println("5 - remove product by 'Id'");
        System.out.println("6 - remove product by 'Name'");
        System.out.println("7 - update product by 'Id'");
        System.out.println("8 - exit");
        System.out.println();
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter number of option:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
