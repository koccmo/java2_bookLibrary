package book_library.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToUiActionMap;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToUiActionMap = new HashMap<>();
        menuNumberToUiActionMap.put(1, findUIAction(uiActions, AddBookUIAction.class));
        menuNumberToUiActionMap.put(2, findUIAction(uiActions, RemoveBookUIAction.class));
        menuNumberToUiActionMap.put(3, findUIAction(uiActions, GetAllBooksUIAction.class));
        menuNumberToUiActionMap.put(4, findUIAction(uiActions, SearchBooksUIAction.class));
        menuNumberToUiActionMap.put(5, findUIAction(uiActions, RegisterReaderUIAction.class));
        menuNumberToUiActionMap.put(6, findUIAction(uiActions, ExitUIAction.class));
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void print() {
        System.out.println("=================================");
        System.out.println("    MARX LIBRARY PROGRAM MENU: ");
        System.out.println("=================================");
        System.out.println("1. Add book to list");
        System.out.println("2. Delete book from list");
        System.out.println("3. Show all books in the list");
        System.out.println("4. Search books");
        System.out.println("5. Register new reader");
        System.out.println("6. Exit");
        System.out.println("=================================");

        System.out.println("");
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item to execute:");
        Scanner scanner = new Scanner(System.in);
        int userChoice = Integer.parseInt(scanner.nextLine());
        return userChoice;
    }

    public void executeSelectedMenuItem(int userChoice) {
        menuNumberToUiActionMap.get(userChoice).execute();
    }
}
