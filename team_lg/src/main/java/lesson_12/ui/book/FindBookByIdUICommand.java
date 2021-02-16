package lesson_12.ui.book;

import lesson_12.core.domain.Book;
import lesson_12.core.requests.book.FindBookByIdRequest;
import lesson_12.core.responses.book.FindBookByIdResponse;
import lesson_12.core.services.book.FindBookByIdService;
import lesson_12.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class FindBookByIdUICommand implements UICommand {

    @Autowired
    private FindBookByIdService findBookByIdService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter Book ID: ");
        String id = scanner.nextLine();

        FindBookByIdRequest request = new FindBookByIdRequest(id);
        FindBookByIdResponse response = findBookByIdService.findBookByIdResponse(request);

        Optional<Book> findBook = response.getFindBookById();

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getErrorField() + " " + coreError.getErrorMessage()));
        } else {
            if (response.getFindBookById().isEmpty()) {
                System.out.println("\nNo book with ID=" + id + " in Electronic library");
            } else {
                Book book = findBook.get();
                System.out.println("Found book in the Electronic library :");
                System.out.print(book.toString() + "\n");
            }
        }
    }
}
