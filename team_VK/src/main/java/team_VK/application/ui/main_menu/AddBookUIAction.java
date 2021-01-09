package team_VK.application.ui.main_menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.AddBookResponse;
import team_VK.application.core.services.main_menu_services.AddBookService;
import team_VK.application.ui.UIActions;
import team_VK.application.ui.additional_function.ErrorsPrinter;

import java.util.Scanner;

@Component
public class AddBookUIAction implements UIActions {

    @Autowired
    private AddBookService addBookService;
    @Autowired private ErrorsPrinter errorsPrinter;

    @Override
    public void execute() {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter book Title :");
        String bookTitle = scanner.nextLine();
        System.out.println("Please enter book Author :");
        String bookAuthor = scanner.nextLine();
        System.out.println("Please enter booking permitted duration");
        int bookingDurationPermitted = scanner.nextInt();

        AddBookRequest request = new AddBookRequest(bookTitle, bookAuthor, bookingDurationPermitted);
        AddBookResponse response = addBookService.addBook(request);

        if (!response.havesError()) {
            System.out.println("Your book was added to list.");
            System.out.println();
        } else {
            errorsPrinter.execute (response);
        }
    }
}
