package book_library;

import book_library.console_ui.*;
import book_library.dependency_injection.ApplicationContext;
import book_library.dependency_injection.DIApplicationContextBuilder;

import java.util.Scanner;

public class BookListApplication {

    private static ApplicationContext applicationContext = new DIApplicationContextBuilder().build("book_library");

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
                AddBookUIAction uiAction = applicationContext.getBean(AddBookUIAction.class);
                uiAction.execute();
                break;
            }
            case 2: {
                RemoveBookUIAction uiAction = applicationContext.getBean(RemoveBookUIAction.class);
                uiAction.execute();
                break;
            }
            case 3: {
                GetAllBooksUIAction uiAction = applicationContext.getBean(GetAllBooksUIAction.class);
                uiAction.execute();
                break;
            }
            case 4: {
                SearchBooksUIAction uiAction = applicationContext.getBean(SearchBooksUIAction.class);
                uiAction.execute();
                break;
            }
            case 5: {
                ExitUIAction uiAction = applicationContext.getBean(ExitUIAction.class);
                uiAction.execute();
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
