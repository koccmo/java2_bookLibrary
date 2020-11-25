package team_VK.application.ui.Client_UI;

import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.core.responses.BookBookResponse;
import team_VK.application.core.services.client_services.BookBookService;
import team_VK.application.database.database_Admin.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.spi.CalendarDataProvider;

public class BookBookUIClientAction implements UIClientActions {

    BookBookService bookBookService;

    public BookBookUIClientAction(BookBookService bookBookService) {
        this.bookBookService = bookBookService;
    }

    @Override
    public void execute() throws ParseException {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter book ID :");
        String bookIDStr = scanner.nextLine();
        long bookID = Long.getLong(bookIDStr);
        System.out.println("Please enter book Title :");
        String bookTitle = scanner.nextLine();
        System.out.println("Please enter book Author :");
        String bookAuthor = scanner.nextLine();

        SimpleDateFormat  formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        System.out.println("Please enter booking start day as dd.mm.yyyy :");
        Date bookingStartDate = formatter.parse("01.01.2020");
        try{
            bookingStartDate = formatter.parse(scanner.nextLine());
        } catch (ParseException e) {e.printStackTrace();}


        BookBookRequest request = new BookBookRequest(bookID, bookTitle, bookAuthor, bookingStartDate );
        BookBookResponse response = bookBookService.bookBook(request);

        if (response.havesError()) {
            System.out.println("The book You have chosen is successfully booked on your name.");
        } else System.out.println(response.errorList.toString());
    }
}
