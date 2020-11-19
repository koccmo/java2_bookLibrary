package team_VK.application;

import team_VK.application.core.services.admin_services.*;
import team_VK.application.database.database_Admin.Database;
import team_VK.application.database.database_Admin.DatabaseInMemory;
import team_VK.application.ui.Admin_UI.AddBookUIAction;
import team_VK.application.ui.Admin_UI.ExitProgramUIAction;
import team_VK.application.ui.Admin_UI.GetBooksListUIAction;
import team_VK.application.ui.Admin_UI.RemoveBookUIAction;

import java.util.Scanner;

public class LibraryAdminApplication {

    public static void main(String[] args) {

        Database database = new DatabaseInMemory();
        AddBookServiceValidator validator = new AddBookServiceValidator();
        AddBookService addBookService = new AddBookService(database, validator);
        AddBookUIAction addBookUIAction = new AddBookUIAction(addBookService);


        RemoveBookService removeBookService = new RemoveBookService(database);
        RemoveBookUIAction removeBookUIAction = new RemoveBookUIAction(removeBookService);

        GetBookListServiceValidator getBookListServiceValidator = new GetBookListServiceValidator();
        GetBooksListService getBooksListService = new GetBooksListService(database, getBookListServiceValidator);
        GetBooksListUIAction getBooksListUIAction = new GetBooksListUIAction(getBooksListService);
        ExitProgramUIAction exitProgramUIAction = new ExitProgramUIAction();


        while (true) {
            showUserMenu();
            switch (userChoice()) {
                case 1: { addBookUIAction.execute(); break; }
                case 2: { removeBookUIAction.execute(); break; }
                case 3: { getBooksListUIAction.execute(); break; }
                case 4: { exitProgramUIAction.execute(); break;}
                }
            }
        }






    private static void showUserMenu() {
        System.out.println("Please select your action.");
        System.out.println("1. Add new book.");
        System.out.println("2. Delete book.");
        System.out.println("3. Get books list.");
        System.out.println("4. Exit program.");
        System.out.println();

    }

    private static int userChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


}
