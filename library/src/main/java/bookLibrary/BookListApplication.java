package bookLibrary;

import bookLibrary.console.ui.*;
import bookLibrary.dependency_injection.ApplicationContext;
import bookLibrary.dependency_injection.DIApplicationContextBuilder;

import java.util.Scanner;

public class BookListApplication {
    private static ApplicationContext context = new DIApplicationContextBuilder().build(
            "bookLibrary");

    public static void main(String[] args) {

        while (true) {
            printMenu();
            int selectMenuField = getMenuFieldFromCustomer();
            executeSelectedMenuField(selectMenuField);
            System.out.println("");
        }
    }

    private static void executeSelectedMenuField(int selectMenuField) {
        switch (selectMenuField) {
            case 1: {
                AddBookUIAction addBookUIAction = context.getBean(AddBookUIAction.class);
                addBookUIAction.execute();
                break;
            }
            case 2: {
                DeleteBookUIAction deleteBookUIAction = context.getBean(DeleteBookUIAction.class);
                deleteBookUIAction.execute();
                break;
            }
            case 3: {
                PrintAllBooksTitleUIAction printAllBooksTitleUIAction = context.getBean(PrintAllBooksTitleUIAction.class);
                printAllBooksTitleUIAction.execute();
                break;
            }
            case 4: {
                FindBookByAuthorUIAction findBookByAuthorUIAction = context.getBean(FindBookByAuthorUIAction.class);
                findBookByAuthorUIAction.execute();
                break;
            }
            case 5:
                SearchBooksUIAction searchBooksUIAction = context.getBean(SearchBooksUIAction.class);
                searchBooksUIAction.execute();
                break;
            case 6:
                FinishWorkUIAction finishWorkUIAction = context.getBean(FinishWorkUIAction.class);
                finishWorkUIAction.execute();
                break;
            default:
                System.out.println("Wrong number True again!");
        }
    }



    private static int getMenuFieldFromCustomer() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        int selectMenuField = Integer.parseInt(scanner.nextLine());
        return selectMenuField;
    }


    private static void printMenu() {
        System.out.println("Enter number for your Action!");
        System.out.println("1 : Add book");
        System.out.println("2 : Remove book");
        System.out.println("3 : Print all books");
        System.out.println("4 : Find buy Author");
        System.out.println("5 : Search Book");
        System.out.println("6 : Finish");

        System.out.println("");
    }



}
