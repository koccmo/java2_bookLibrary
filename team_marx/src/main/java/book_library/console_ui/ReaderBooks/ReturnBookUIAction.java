package book_library.console_ui.ReaderBooks;

import book_library.console_ui.UIAction;
import book_library.core.requests.ReaderBook.ReturnBookRequest;
import book_library.core.responses.ReaderBook.ReturnBookResponse;
import book_library.core.services.ReaderBooks.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Component
public class ReturnBookUIAction implements UIAction {

    @Autowired
    private ReturnBookService returnBookService;

    @Override
    public void execute() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter reader ID: ");
        String readerIdString = scanner.nextLine();
        System.out.println("Enter book ID: ");
        String bookIdString = scanner.nextLine();
        System.out.println("Enter date and time book was returned (yyyy/MM/dd HH:mm): ");
        String bookReturnDateString = scanner.nextLine();

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

        Date bookReturnDate = null;
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        if (bookReturnDateString.isEmpty()) {
            bookReturnDate = null;
        } else {
            bookReturnDate = formatter1.parse(bookReturnDateString);
        }
        ReturnBookRequest request = new ReturnBookRequest(readerId, bookId, bookReturnDate);
        ReturnBookResponse response = returnBookService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("The returning of the book has been successfully registered.");
            System.out.println("The record in readerBook with id : " + response.getUpdatedReaderBookId() + " was updated");
        }
    }
}
