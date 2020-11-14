package team_VK.application.ui;

import team_VK.application.responses.AddBookResponse;
import team_VK.application.services.AddBookService;
import team_VK.application.requests.AddBookRequest;
import java.util.Scanner;


public class AddBookUIAction implements UIActions {

    private final AddBookService addBookService;

    public AddBookUIAction(AddBookService addBookService) {
        this.addBookService = addBookService;
    }

    @Override
    public void execute() {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter book Title :");
        String bookTitle = scanner.nextLine();
        System.out.println("Please enter book Author :");
        String bookAuthor = scanner.nextLine();

        AddBookRequest request = new AddBookRequest(bookTitle, bookAuthor);
        AddBookResponse response = addBookService.addBook(request);

        if (!response.havesError()) {
            System.out.println("Your book was added to list.");
            System.out.println();
        } else {
            response.getAddBookErrorList().forEach(coreError -> System.out.println(coreError.toString()));
        }
    }
}
