package book_library;

import book_library.console_ui.AddBookUIAction;
import book_library.console_ui.ExitUIAction;
import book_library.console_ui.GetAllBooksUIAction;
import book_library.console_ui.RemoveBookUIAction;
import book_library.core.database.Database;
import book_library.core.database.InMemoryDataBaseImpl;
import book_library.core.services.AddBookService;
import book_library.core.validators.AddBookValidator;
import book_library.core.services.GetAllBooksService;
import book_library.core.services.RemoveBookService;

import java.util.Scanner;

public class BookListApplication {

    private static Database database = new InMemoryDataBaseImpl();
    private static AddBookValidator addBookValidator = new AddBookValidator(database);
    private static AddBookService addBookService = new AddBookService(database, addBookValidator);
    private static RemoveBookService removeBookService = new RemoveBookService(database);
    private static GetAllBooksService getAllBooksService = new GetAllBooksService(database);
    private static AddBookUIAction addBookUIAction = new AddBookUIAction(addBookService);
    private static RemoveBookUIAction removeBookUIAction = new RemoveBookUIAction(removeBookService);
    private static GetAllBooksUIAction getAllBooksUIAction = new GetAllBooksUIAction(getAllBooksService);
    private static ExitUIAction exitUIAction = new ExitUIAction();

    public static void main(String[] args) {

        while (true){
            printProgramMenu();

            int userChoice = getMenuNumberFromUser();

            executeSelectedMenuItem(userChoice);
        }
    }

    private static void executeSelectedMenuItem(int userChoice) {
        switch (userChoice){
            case 1:{
                addBookUIAction.execute();
                break;
            }
            case 2:{
                removeBookUIAction.execute();
                break;
            }
            case 3:{
                getAllBooksUIAction.execute();
                break;
            }
            case 4:{
                exitUIAction.execute();
                break;
            }
        }

        System.out.println("");
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item to execute:");
        Scanner scanner = new Scanner(System.in);
        int userChoice = Integer.parseInt(scanner.nextLine());
        return userChoice;
    }

    private static void printProgramMenu() {
        System.out.println("=================================");
        System.out.println("    MARX LIBRARY PROGRAM MENU: ");
        System.out.println("=================================");
        System.out.println("1. Add book to list");
        System.out.println("2. Delete book from list");
        System.out.println("3. Show all books in the list");
        System.out.println("4. Exit");
        System.out.println("=================================");

        System.out.println("");
    }
}
