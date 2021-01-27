package lesson_10.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ApplicationMenu {

    private Map<Integer, UICommand> menuNumberToUICommandMap = new HashMap<>();

    @Autowired
    public ApplicationMenu(List<UICommand> uiCommands) {
        menuNumberToUICommandMap.put(1, findUICommand(uiCommands, AddBookUICommand.class));
        menuNumberToUICommandMap.put(2, findUICommand(uiCommands, DeleteBookByTitleUICommand.class));
        menuNumberToUICommandMap.put(3, findUICommand(uiCommands, DeleteBookByAuthorUICommand.class));
        menuNumberToUICommandMap.put(4, findUICommand(uiCommands, DeleteBookByIdUICommand.class));
        menuNumberToUICommandMap.put(5, findUICommand(uiCommands, GetAllBooksUICommand.class));
        menuNumberToUICommandMap.put(6, findUICommand(uiCommands, FindBookByTitleUICommand.class));
        menuNumberToUICommandMap.put(7, findUICommand(uiCommands, FindBookByAuthorUICommand.class));
        menuNumberToUICommandMap.put(8, findUICommand(uiCommands, FindBookByIdUICommand.class));
        menuNumberToUICommandMap.put(0, findUICommand(uiCommands, ExitUICommand.class));
    }

    public int getMenuNumberFromUser() {
        System.out.println("Please enter menu item number:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void printMenu() {
        System.out.println("\n==============================================================");
        System.out.println("  ELECTRONIC LIBRARY PROGRAM FUNCTIONAL MENU:");
        System.out.println("==============================================================");
        System.out.println("1. Add book to electronic library");
        System.out.println("2. Delete book from electronic library by title");
        System.out.println("3. Delete book from electronic library by author");
        System.out.println("4. Delete book from electronic library by Id");
        System.out.println("5. Print all books in electronic library");
        System.out.println("6. Find all books in electronic library by title");
        System.out.println("7. Find all books in electronic library by author");
        System.out.println("8. Find book by Id in electronic library");
        System.out.println("0. Exit program menu");
        System.out.println("==============================================================");
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        UICommand uiCommand = menuNumberToUICommandMap.get(selectedMenu);
        if (uiCommand != null) {
            uiCommand.execute();
        } else {
            System.out.println("\nMenu item " + selectedMenu + " don't exist, please enter menu item number:");
        }
    }

    private UICommand findUICommand(List<UICommand> uiCommands, Class uiCommandClass) {
        return uiCommands.stream()
                .filter(uiCommand -> uiCommand.getClass().equals(uiCommandClass))
                .findFirst()
                .get();
    }
}
