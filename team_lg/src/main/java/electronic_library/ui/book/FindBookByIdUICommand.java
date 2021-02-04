package electronic_library.ui.book;

import electronic_library.core.domain.Book;
import electronic_library.core.requests.book.FindBookByIdRequest;
import electronic_library.core.responses.book.FindBookByIdResponse;
import electronic_library.core.services.book.FindBookByIdService;
import electronic_library.ui.UICommand;
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
