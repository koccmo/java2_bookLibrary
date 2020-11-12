package team_VK.application;

import team_VK.application.database.Database;
import team_VK.application.database.DatabaseInMemory;
import team_VK.application.services.AddBookService;
import team_VK.application.services.GetBooksListService;
import team_VK.application.services.RemoveBookService;
import team_VK.application.ui.AddBookUIAction;
import team_VK.application.ui.ExitProgramUIAction;
import team_VK.application.ui.GetBooksListUIAction;
import team_VK.application.ui.RemoveBookUIAction;

import java.util.Scanner;

public class LibraryDemo {

    public static void main(String[] args) {

        Database database = new DatabaseInMemory();
        AddBookService addBookService = new AddBookService(database);
        AddBookUIAction addBookUIAction = new AddBookUIAction(addBookService);

        RemoveBookService removeBookService = new RemoveBookService(database);
        RemoveBookUIAction removeBookUIAction = new RemoveBookUIAction(removeBookService);

        GetBooksListService getBooksListService = new GetBooksListService(database);
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
