package book_library.console_ui.Book;

import book_library.console_ui.UIAction;
import book_library.core.requests.Book.RemoveBookRequest;
import book_library.core.responses.Book.RemoveBookResponse;
import book_library.core.services.Book.RemoveBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveBookUIAction implements UIAction {

    @Autowired
    private RemoveBookService removeBookService;

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
