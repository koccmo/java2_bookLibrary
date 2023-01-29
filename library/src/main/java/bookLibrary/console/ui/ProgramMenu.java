package bookLibrary.console.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddBookUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, DeleteBookUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, PrintAllBooksTitleUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, FindBookByAuthorUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, SearchBooksUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, GetBookIdUIAction.class));
        menuNumberToUIActionMap.put(7, findUIAction(uiActions, ReaderRegisteringUIAction.class));
        menuNumberToUIActionMap.put(8, findUIAction(uiActions, ReaderSearchUIAction.class));
        menuNumberToUIActionMap.put(9, findUIAction(uiActions, TakeBookUIAction.class));
        menuNumberToUIActionMap.put(10, findUIAction(uiActions, FinishWorkUIAction.class));

    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public int getMenuFieldFromCustomer() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }


    public void printMenu() {
        System.out.println("Enter number for your Action!");
        System.out.println("1 : Add book");
        System.out.println("2 : Remove book");
        System.out.println("3 : Print all books");
        System.out.println("4 : Find buy Author");
        System.out.println("5 : Search Book");
        System.out.println("6 : Get Book ID");
        System.out.println("7 : Reader Registering");
        System.out.println("8 : Search Readers");
        System.out.println("9 : Take book");
        System.out.println("10 : Finish");

        System.out.println("");
    }

    public void executeSelectedMenuItem (int selectedMenuField) {
        menuNumberToUIActionMap.get(selectedMenuField).execute();
    }

}
