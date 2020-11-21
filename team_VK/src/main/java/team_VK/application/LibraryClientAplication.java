package team_VK.application;

import team_VK.application.core.domain.Book;
import team_VK.application.core.services.admin_services.GetBookListServiceValidator;
import team_VK.application.database.database_Admin.DataBaseFiller;
import team_VK.application.database.database_Admin.Database;
import team_VK.application.database.database_Admin.DatabaseInMemory;
import team_VK.application.database.database_Clients.DataBaseClientFiller;
import team_VK.application.database.database_Clients.DatabaseClients;
import team_VK.application.database.database_Clients.DatabaseClientsInMemory;
import team_VK.application.core.services.admin_services.GetBooksListService;
import team_VK.application.core.services.client_services.AddClientService;
import team_VK.application.core.services.client_services.ShowBookService;
import team_VK.application.ui.Client_UI.AddClientUIClientActions;
import team_VK.application.ui.Client_UI.ShowBookUIClientActions;
import team_VK.application.ui.Admin_UI.ExitProgramUIAction;
import team_VK.application.ui.Admin_UI.GetBooksListUIAction;

import java.util.Scanner;

public class LibraryClientAplication {

    public static void main(String[] args) {

        DatabaseClients databaseClient = new DatabaseClientsInMemory();
        Database database = new DatabaseInMemory();

        database.addBook(new Book("Foo", "Bar"));
        database.addBook(new Book("Buz", "Qux"));
        database.addBook(new Book("Lorem", "Ipsum"));


        GetBookListServiceValidator getBookListServiceValidator = new GetBookListServiceValidator();
        GetBooksListService getBooksListService = new GetBooksListService(database, getBookListServiceValidator);
        GetBooksListUIAction getBooksListUIAction = new GetBooksListUIAction(getBooksListService);

        ShowBookService showBookService = new ShowBookService(database);
        ShowBookUIClientActions showBookUIClientActions = new ShowBookUIClientActions(showBookService);


        AddClientService addClientService = new AddClientService(databaseClient);
        AddClientUIClientActions addClientUIClientActions = new AddClientUIClientActions(addClientService);

        ExitProgramUIAction exitProgramUIAction = new ExitProgramUIAction();

        DataBaseFiller DBFiller = new DataBaseFiller();
        DBFiller.fill();

        DataBaseClientFiller DBClientFiller = new DataBaseClientFiller();
        DBClientFiller.fill();

        while (true) {
            showClientMenu();
            switch (clientChoice()) {
//            case 1: // Log in}
                case 2:
                    getBooksListUIAction.execute();
                    break;
                case 3: showBookUIClientActions.execute(); break;
//                case 4: // Book book
                case 5:
                    addClientUIClientActions.execute();
                    break;
                case 6:
                    exitProgramUIAction.execute();
                    break;

            }

        }
    }

    private static void showClientMenu() {
        System.out.println("Please select your action.");
        System.out.println("1. Log in.");
        System.out.println("2. Get books list.");
        System.out.println("3. Select book.");
        System.out.println("4. Book book.");
        System.out.println("5. Register new Client.");
        System.out.println("6. Exit.");
        System.out.println();

    }

    private static int clientChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}

