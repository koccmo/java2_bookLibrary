package book_library.console_ui;

import book_library.core.requests.AddBookRequest;
import book_library.core.responses.AddBookResponse;
import book_library.core.services.AddBookService;
import book_library.dependency_injection.DIComponent;
import book_library.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class AddBookUIAction implements UIAction {

    @DIDependency private AddBookService addBookService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book tittle:");
        String bookTitle = scanner.nextLine();
        System.out.println("Enter book author:");
        String bookAuthor = scanner.nextLine();
        AddBookRequest request = new AddBookRequest(bookTitle, bookAuthor);
        AddBookResponse response = addBookService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("New book id is: " + response.getNewBook().getId());
            System.out.println("Your book was added to list.");
        }

    }
}
