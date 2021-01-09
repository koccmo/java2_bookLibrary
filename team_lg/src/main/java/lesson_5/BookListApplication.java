package lesson_5;

import lesson_5.ui.*;

import java.util.Scanner;

public class BookListApplication {

    private static final ApplicationContext applicationContext = new ApplicationContext();

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
                AddBookUICommand uiCommand = applicationContext.getBean(AddBookUICommand.class);
                uiCommand.execute();
                break;
            }
            case 2: {
                DeleteBookByTitleUICommand uiCommand = applicationContext.getBean(DeleteBookByTitleUICommand.class);
                uiCommand.execute();
                break;
            }
            case 3: {
                DeleteBookByAuthorUICommand uiCommand = applicationContext.getBean(DeleteBookByAuthorUICommand.class);
                uiCommand.execute();
                break;
            }
            case 4: {
                DeleteBookByIdUICommand uiCommand = applicationContext.getBean(DeleteBookByIdUICommand.class);
                uiCommand.execute();
                break;
            }
            case 5: {
                GetAllBooksUICommand uiCommand = applicationContext.getBean(GetAllBooksUICommand.class);
                uiCommand.execute();
                break;
            }
            case 6: {
                FindBookByTitleUICommand uiCommand = applicationContext.getBean(FindBookByTitleUICommand.class);
                uiCommand.execute();
                break;
            }
            case 7: {
                FindBookByAuthorUICommand uiCommand = applicationContext.getBean(FindBookByAuthorUICommand.class);
                uiCommand.execute();
                break;
            }
            case 8: {
                FindBookByIdUICommand uiCommand = applicationContext.getBean(FindBookByIdUICommand.class);
                uiCommand.execute();
                break;
            }
            case 0: {
                ExitUICommand uiCommand = applicationContext.getBean(ExitUICommand.class);
                uiCommand.execute();
                break;
            }
        }
    }
}


