package team_VK.application.ui.main_menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.core.responses.BookBookResponse;
import team_VK.application.core.services.main_menu_services.BookBookService;
import team_VK.application.ui.UIActions;
import team_VK.application.ui.additional_function.ErrorsPrinter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
@Component
public class BookBookUIAction implements UIActions {

    @Autowired
    private BookBookService bookBookService;
    @Autowired private ErrorsPrinter errorsPrinter;

    @Override
    public void execute() throws ParseException {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter book ID :");
        String bookIDStr = scanner.nextLine();
        long bookID = (long)Long.valueOf(bookIDStr).longValue();

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

        if (!response.havesError()) {
            System.out.println("The book You have chosen is successfully booked on your name.");
        } else  errorsPrinter.execute (response);
    }
}
