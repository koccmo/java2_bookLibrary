package team_VK.application;

import team_VK.application.core.services.*;
import team_VK.application.database.DataBaseFiller;
import team_VK.application.database.Database;
import team_VK.application.database.DatabaseInMemory;
import team_VK.application.database.DataBaseClientFiller;
import team_VK.application.database.DatabaseClients;
import team_VK.application.database.DatabaseClientsInMemory;
import team_VK.application.ui.AddBookUIAction;
import team_VK.application.ui.ExitProgramUIAction;
import team_VK.application.ui.GetBooksListUIAction;
import team_VK.application.ui.RemoveBookUIAction;
import team_VK.application.ui.AddClientUIActions;
import team_VK.application.ui.BookBookUIAction;
import team_VK.application.ui.ShowBookUIActions;

import java.text.ParseException;
import java.util.Scanner;

public class LibraryApplication {

    public static void main(String[] args) throws ParseException {

        ApplicationContext context = new ApplicationContext();


//        Database database = new DatabaseInMemory();
//        DataBaseFiller DBFiller = new DataBaseFiller(database);
//        DBFiller.fill();
//
//        DatabaseClients databaseClient = new DatabaseClientsInMemory();
//        DataBaseClientFiller DBClientFiller = new DataBaseClientFiller(databaseClient);
//        DBClientFiller.fill();
//
//
//        AddBookServiceValidator validator = new AddBookServiceValidator();
//        AddBookService addBookService = new AddBookService(database, validator);
//        AddBookUIAction addBookUIAction = new AddBookUIAction(addBookService);
//
//        RemoveBookServiceValidator removeBookServiceValidator = new RemoveBookServiceValidator();
//        RemoveBookService removeBookService = new RemoveBookService(database, removeBookServiceValidator);
//        RemoveBookUIAction removeBookUIAction = new RemoveBookUIAction(removeBookService);
//
//        GetBooksListServiceValidator getBooksListServiceValidator = new GetBooksListServiceValidator();
//        GetBooksListService getBooksListService = new GetBooksListService(database, getBooksListServiceValidator);
//        GetBooksListUIAction getBooksListUIAction = new GetBooksListUIAction(getBooksListService);
//
//        BookBookServiceValidator bookBookServiceValidator = new BookBookServiceValidator();
//        BookBookService bookBookService = new BookBookService(database,bookBookServiceValidator);
//        BookBookUIAction bookBookUIAction = new BookBookUIAction(bookBookService);
//
//        ShowBookService showBookService = new ShowBookService(database);
//        ShowBookUIActions showBookUIActions = new ShowBookUIActions(showBookService);
//
//        AddClientServiceValidator addClientServiceValidator = new AddClientServiceValidator();
//        AddClientService addClientService = new AddClientService(databaseClient, addClientServiceValidator);
//        AddClientUIActions addClientUIActions = new AddClientUIActions(addClientService);
//
//        ExitProgramUIAction exitProgramUIAction = new ExitProgramUIAction();


        while (true) {
            showUserMenu();
            switch (userChoice()) {
                case 1: {
                    AddBookUIAction addBookUIAction = context.getBean(AddBookUIAction.class);
                    addBookUIAction.execute();
                    break;
                }

                case 2: {
                    RemoveBookUIAction removeBookUIAction = context.getBean(RemoveBookUIAction.class);
                    removeBookUIAction.execute();
                    break;
                }
                case 3: {
                    GetBooksListUIAction getBooksListUIAction = context.getBean(GetBooksListUIAction.class);
                    getBooksListUIAction.execute();
                    break;
                }
                case 4:
                    ShowBookUIActions showBookUIActions = context.getBean(ShowBookUIActions.class);
                    showBookUIActions.execute();
                    break;
                case 5:
                    BookBookUIAction bookBookUIAction = context.getBean(BookBookUIAction.class);
                    bookBookUIAction.execute();
                    break;
                case 6:
                    AddClientUIActions addClientUIActions = context.getBean(AddClientUIActions.class);
                    addClientUIActions.execute();
                    break;
                case 7: {
                    ExitProgramUIAction exitProgramUIAction = context.getBean(ExitProgramUIAction.class);
                    exitProgramUIAction.execute();
                    break;
                }
            }
        }
    }


    private static void showUserMenu() {
        System.out.println("Please select your action.");
        System.out.println("1. Add new book.");
        System.out.println("2. Delete book.");
        System.out.println("3. Get books list.");
        System.out.println("4. Show book parameters by ID.");
        System.out.println("5. Book book.");
        System.out.println("6. Register new Client.");
        System.out.println("7. Exit program.");
        System.out.println();

    }

    private static int userChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


}
