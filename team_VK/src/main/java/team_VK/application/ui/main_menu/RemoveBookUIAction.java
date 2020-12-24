package team_VK.application.ui.main_menu;

import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.main_menu_services.RemoveBookService;
import team_VK.application.database.DIComponent;
import team_VK.application.ui.UIActions;
import team_VK.application.ui.additional_function.ErrorsPrinter;

import java.util.Scanner;
@DIComponent
public class RemoveBookUIAction implements UIActions {

    @DIDependency private RemoveBookService removeBookService ;
    @DIDependency private ErrorsPrinter errorsPrinter;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Book ID: ");
        String bookIDSt = scanner.nextLine();
        long bookID = Long.decode(bookIDSt);

        System.out.println("Book Title: ");
        String bookTitle = scanner.nextLine();

        RemoveBookRequest request = new RemoveBookRequest(bookID, bookTitle);
        RemoveBookResponse response = removeBookService.removeBook(request);

        if(!response.havesError()) {
            System.out.println("Your book was removed from list.");
        } else errorsPrinter.execute (response);;
    }
}
