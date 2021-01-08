package book_library.console_ui;

import book_library.core.requests.RemoveBookRequest;
import book_library.core.responses.RemoveBookResponse;
import book_library.core.services.RemoveBookService;
import book_library.dependency_injection.DIComponent;
import book_library.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class RemoveBookUIAction implements UIAction {

    @DIDependency private RemoveBookService removeBookService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id to remove:");
        Long bookId = Long.parseLong(scanner.nextLine());
        RemoveBookRequest request = new RemoveBookRequest(bookId);
        RemoveBookResponse response = removeBookService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.isBookRemoved()) {
                System.out.println("Your book was removed from the list.");
            } else {
                System.out.println("Your book was not removed from the list.");
            }
        }
    }
}
