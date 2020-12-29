package book_library;

import book_library.console_ui.*;
import book_library.core.database.Database;
import book_library.core.database.InMemoryDataBaseImpl;
import book_library.core.services.AddBookService;
import book_library.core.services.SearchBooksService;
import book_library.core.validators.AddBookRequestValidator;
import book_library.core.services.GetAllBooksService;
import book_library.core.services.RemoveBookService;
import book_library.core.validators.RemoveBookRequestValidator;
import book_library.core.validators.SearchBooksRequestValidator;

import java.util.Scanner;

public class BookListApplication {

    private static Database database = new InMemoryDataBaseImpl();

    private static AddBookRequestValidator addBookValidator = new AddBookRequestValidator(database);
    private static RemoveBookRequestValidator removeBookValidator = new RemoveBookRequestValidator(database);
    private static SearchBooksRequestValidator searchBooksRequestValidator = new SearchBooksRequestValidator();

    private static AddBookService addBookService = new AddBookService(database, addBookValidator);
    private static RemoveBookService removeBookService = new RemoveBookService(database, removeBookValidator);
    private static GetAllBooksService getAllBooksService = new GetAllBooksService(database);
    private static SearchBooksService searchBooksService = new SearchBooksService(database, searchBooksRequestValidator);

    private static AddBookUIAction addBookUIAction = new AddBookUIAction(addBookService);
    private static RemoveBookUIAction removeBookUIAction = new RemoveBookUIAction(removeBookService);
    private static GetAllBooksUIAction getAllBooksUIAction = new GetAllBooksUIAction(getAllBooksService);
    private static ExitUIAction exitUIAction = new ExitUIAction();
    private static SearchBooksUIAction searchBooksUIAction = new SearchBooksUIAction(searchBooksService);

    public static void main(String[] args) {

        while (true) {
            printProgramMenu();

            int userChoice = getMenuNumberFromUser();

            executeSelectedMenuItem(userChoice);
        }
    }

    private static void executeSelectedMenuItem(int userChoice) {
        switch (userChoice) {
            case 1: {
                addBookUIAction.execute();
                break;
            }
            case 2: {
                removeBookUIAction.execute();
                break;
            }
            case 3: {
                getAllBooksUIAction.execute();
                break;
            }
            case 4: {
                searchBooksUIAction.execute();
                break;
            }
            case 5: {
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
        System.out.println("4. Search books");
        System.out.println("5. Exit");
        System.out.println("=================================");

        System.out.println("");
    }
}
