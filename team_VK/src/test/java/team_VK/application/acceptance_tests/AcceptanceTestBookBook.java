package team_VK.application.acceptance_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import team_VK.application.configuration.LibraryConfig;
import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.core.responses.BookBookResponse;
import team_VK.application.core.services.main_menu_services.BookBookService;
import team_VK.application.database.Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AcceptanceTestBookBook {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(LibraryConfig.class);

//        DataBaseFillAdditionalFunction dataBaseFillAdditionalFunction =
//                appContext.getBean(DataBaseFillAdditionalFunction.class);
//
//        dataBaseFillAdditionalFunction.execute();

    }

    @Test
    public void ShouldBookForCorrectBookAndPeriod() throws ParseException {
        BookBookService service = appContext.getBean(BookBookService.class);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        BookBookRequest request =
                new BookBookRequest(6, "Good bay, weapon", "Hemingway E.", formatter.parse("01.01.2022"));

        BookBookResponse response = service.bookBook(request);
        Assert.assertEquals(response.errorList.size(), 0);
        Assert.assertEquals(appContext.getBean(Database.class).getListBooks().get(5).bookings.get(0).getBookingFinishDate(),formatter.parse("06.01.2022"));
    }
}
