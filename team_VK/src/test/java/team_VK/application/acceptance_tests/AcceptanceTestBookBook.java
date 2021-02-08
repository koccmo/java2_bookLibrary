package team_VK.application.acceptance_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.core.responses.BookBookResponse;
import team_VK.application.core.services.main_menu_services.BookBookService;
import team_VK.application.database.BookingPeriodsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AcceptanceTestBookBook {

    @Autowired
    private BookingPeriodsRepository bookingPeriods;
    @Autowired
    private BookBookService service;

    @Before
    public void setup() {
    }

    @Test
    public void ShouldBookForCorrectBookAndPeriod() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Book book = new Book("Good bay, weapon", "Hemingway E.", 5);
        BookBookRequest request =
                new BookBookRequest(6, book.getBookTitle(), book.getBookAuthor(),
                        formatter.parse("01.01.2022"));

        BookBookResponse response = service.bookBook(request);
        Assert.assertEquals(response.errorList.size(), 0);
        Assert.assertEquals(bookingPeriods.getBookingsList(book).get(0).getBookingFinishDate(),formatter.parse("06.01.2022"));
    }
}
