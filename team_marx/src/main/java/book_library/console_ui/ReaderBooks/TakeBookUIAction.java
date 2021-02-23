package book_library.console_ui.ReaderBooks;

import book_library.console_ui.UIAction;
import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.ReaderBook.TakeBookResponse;
import book_library.core.services.ReaderBooks.TakeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Component
public class TakeBookUIAction implements UIAction {

    @Autowired
    private TakeBookService takeBookService;

    @Override
    public void execute() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reader ID: ");
        String readerIdString = scanner.nextLine();
        System.out.println("Enter book ID: ");
        String bookIdString = scanner.nextLine();
        System.out.println("Enter date and time book was taken (yyyy/MM/dd HH:mm): ");
        String bookOutDateString = scanner.nextLine();

        Long readerId = null;
        if (readerIdString.isEmpty()) {
            readerId = null;
        } else {
            readerId = Long.parseLong(readerIdString);
        }

        Long bookId = null;
        if (bookIdString.isEmpty()) {
            bookId = null;
        } else {
            bookId = Long.parseLong(bookIdString);
        }

        Date bookOutDate = null;
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        if (bookOutDateString.isEmpty()) {
            bookOutDate = null;
        } else {
            bookOutDate = formatter1.parse(bookOutDateString);
        }
        TakeBookRequest request = new TakeBookRequest(readerId, bookId, bookOutDate);
        TakeBookResponse response = takeBookService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("The taking of the book has been successfully registered.");
            System.out.println("New readerBook id is: " + response.getNewReaderBook().getId());
        }
    }
}
