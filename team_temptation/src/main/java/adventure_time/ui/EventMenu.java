package adventure_time.ui;

import adventure_time.ui.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


@Component
public class EventMenu {

    private Map<Integer, UIAction> menuItemsMap;

    @Autowired
    public EventMenu (List<UIAction> uiActionList) {
        menuItemsMap = new HashMap<>();
        menuItemsMap.put(1, findUIAction(uiActionList, AddEventUIAction.class));
        menuItemsMap.put(2, findUIAction(uiActionList, RemoveEventUIAction.class));
        menuItemsMap.put(3, findUIAction(uiActionList, SearchEventUIAction.class));
        menuItemsMap.put(4, findUIAction(uiActionList, UpdateUIAction.class));
        menuItemsMap.put(5, findUIAction(uiActionList, DisplayEventUIAction.class));
        menuItemsMap.put(6, findUIAction(uiActionList, ExitEventUIAction.class));
        menuItemsMap.put(7, findUIAction(uiActionList, StartUpEventUIAction.class));
    // Сюда добавлять новые пункты меню, ну и, конечно же, в menuForEvents()
    }

    public void executeSelectedMenuItem(int selectedMenuItem) {
        menuItemsMap.get(selectedMenuItem).execute();
    }

    private UIAction findUIAction(List<UIAction> uiActionList, Class uiActionClass) {
        return uiActionList.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void menuForEvents() {
        System.out.println("EVENTS:");
        System.out.println("1. Add new event");
        System.out.println("2. Delete an event");
        System.out.println("3. Search events");
        System.out.println("4. Update an event");
        System.out.println("5. Show all events");
        System.out.println("6. Return to Main menu");
        System.out.println("7. Start with the defined DB");
        System.out.println();
    }

    public void mainMenu() {
        System.out.println("PROGRAM MENU:");
        System.out.println("1. CUSTOMERS");
        System.out.println("2. GUIDES");
        System.out.println("3. EVENTS");
        System.out.println("4. TOURS");
        System.out.println("5. TICKETS");
        System.out.println("6. EXIT");
        System.out.println();
    }

    public int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

}
