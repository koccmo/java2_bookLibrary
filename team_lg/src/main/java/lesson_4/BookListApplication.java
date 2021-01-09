package lesson_4;

import lesson_4.core.database.ElectronicLibrary;
import lesson_4.core.database.ElectronicLibraryImpl;

import lesson_4.core.services.*;
import lesson_4.core.services.validators.*;
import lesson_4.ui.*;

import java.util.Scanner;

public class BookListApplication {

    private static final ElectronicLibrary electronicLibrary = new ElectronicLibraryImpl();

    private static final AddBookValidator addBookValidator = new AddBookValidator();
    private static final AddBookService addBookService = new AddBookService(electronicLibrary, addBookValidator);
    private static final UICommand addBookUICommand = new AddBookUICommand(addBookService);

    private static final DeleteBookByTitleValidator deleteBookByTitleValidator = new DeleteBookByTitleValidator();
    private static final DeleteBookByTitleService deleteBookByTitleService = new DeleteBookByTitleService(electronicLibrary, deleteBookByTitleValidator);
    private static final UICommand deleteBookByTitleUICommand = new DeleteBookByTitleUICommand(deleteBookByTitleService);

    private static final DeleteBookByAuthorValidator deleteBookByAuthorValidator = new DeleteBookByAuthorValidator();
    private static final DeleteBookByAuthorService deleteBookByAuthorService = new DeleteBookByAuthorService(electronicLibrary, deleteBookByAuthorValidator);
    private static final UICommand deleteBookByAuthorUICommand = new DeleteBookByAuthorUICommand(deleteBookByAuthorService);

    private static final DeleteBookByIdValidator deleteBookByIdValidator = new DeleteBookByIdValidator();
    private static final DeleteBookByIdService deleteBookByIdService = new DeleteBookByIdService(electronicLibrary, deleteBookByIdValidator);
    private static final UICommand deleteBookByIdUICommand = new DeleteBookByIdUICommand(deleteBookByIdService);

    private static final GetAllBooksValidator getAllBooksValidator = new GetAllBooksValidator();
    private static final GetAllBooksService getAllBooksService = new GetAllBooksService(electronicLibrary, getAllBooksValidator);
    private static final UICommand getAllBooksUICommand = new GetAllBooksUICommand(getAllBooksService);

    private static final FindBooksRequestValidator findBooksByTitleRequestValidator = new FindBooksRequestValidator();
    private static final FindBooksService findBooksByTitleService = new FindBooksService(electronicLibrary, findBooksByTitleRequestValidator);
    private static final UICommand findBookByTitleUICommand = new FindBookByTitleUICommand(findBooksByTitleService);

    private static final FindBooksRequestValidator findBooksByAuthorRequestValidator = new FindBooksRequestValidator();
    private static final FindBooksService findBooksByAuthorService = new FindBooksService(electronicLibrary, findBooksByAuthorRequestValidator);
    private static final UICommand findBookByAuthorUICommand = new FindBookByAuthorUICommand(findBooksByAuthorService);

    private static final FindBookByIdValidator findBookByIdValidator = new FindBookByIdValidator();
    private static final FindBookByIdService findBookByIdService = new FindBookByIdService(electronicLibrary, findBookByIdValidator);
    private static final UICommand findBookByIdUICommand = new FindBookByIdUICommand(findBookByIdService);

    private static final UICommand exitUICommand = new ExitUICommand();

    public static void main(String[] args) {
        while (true) {
            applicationMenu();
            try {
                int menuValue = getMenuNumberFromUser();
                selectedMenu(menuValue);
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect input, please enter number");
            }
        }
    }

    private static void applicationMenu() {
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

    private static int getMenuNumberFromUser() {
        System.out.println("Please enter menu item number:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void selectedMenu(int menuValue) {
        switch (menuValue) {
            case 1: {
                addBookUICommand.execute();
                break;
            }
            case 2: {
                deleteBookByTitleUICommand.execute();
                break;
            }
            case 3: {
                deleteBookByAuthorUICommand.execute();
                break;
            }
            case 4: {
                deleteBookByIdUICommand.execute();
                break;
            }
            case 5: {
                getAllBooksUICommand.execute();
                break;
            }
            case 6: {
                findBookByTitleUICommand.execute();
                break;
            }
            case 7: {
                findBookByAuthorUICommand.execute();
                break;
            }
            case 8: {
                findBookByIdUICommand.execute();
                break;
            }
            case 0: {
                exitUICommand.execute();
                break;
            }
        }
    }
}


