package team_VK.application.ui;

import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.core.services.DIDependency;
import team_VK.application.core.services.RemoveBookService;
import team_VK.application.database.DIComponent;

import java.util.Scanner;
@DIComponent
public class RemoveBookUIAction implements UIActions {

    @DIDependency private RemoveBookService removeBookService ;

//    public RemoveBookUIAction(RemoveBookService removeBookService) {
//        this.removeBookService = removeBookService;
//    }

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
        } else System.out.println(response.getErrorList().toString());
    }
}
