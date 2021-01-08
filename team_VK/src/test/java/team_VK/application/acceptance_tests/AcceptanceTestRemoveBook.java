package team_VK.application.acceptance_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import team_VK.application.configuration.LibraryConfig;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.core.services.additional_functions.DataBaseFillAdditionalFunction;
import team_VK.application.core.services.main_menu_services.RemoveBookService;
import team_VK.application.database.DatabaseInMemory;

public class AcceptanceTestRemoveBook {


    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(LibraryConfig.class);

        DataBaseFillAdditionalFunction dataBaseFillAdditionalFunction =
                appContext.getBean(DataBaseFillAdditionalFunction.class);

        dataBaseFillAdditionalFunction.execute();

    }

    @Test
    public void shouldRemoveCorrectBook(){

        int bookNumberBeforeTest = appContext.getBean(DatabaseInMemory.class).getListBooks().size();

        RemoveBookService removeBookService = appContext.getBean(RemoveBookService.class);
        RemoveBookRequest removeBookRequest = new RemoveBookRequest(6, "Good bay, weapon");
        RemoveBookResponse removeBookResponse = removeBookService.removeBook(removeBookRequest);


        Assert.assertFalse(removeBookResponse.havesError());
        Assert.assertEquals(removeBookResponse.errorList.size(), 0);
        Assert.assertEquals(appContext.getBean(DatabaseInMemory.class).getListBooks().size(), bookNumberBeforeTest-1);
        System.out.println();
    }

    @Test
    public void shouldNotRemoveInCorrectBook(){

        int bookNumberBeforeTest = appContext.getBean(DatabaseInMemory.class).getListBooks().size();

        RemoveBookService removeBookService = appContext.getBean(RemoveBookService.class);
        RemoveBookRequest removeBookRequest = new RemoveBookRequest(7, "Good bay, weapon");
        RemoveBookResponse removeBookResponse = removeBookService.removeBook(removeBookRequest);


        Assert.assertTrue(removeBookResponse.havesError());
        Assert.assertEquals(removeBookResponse.errorList.size(), 1);
        Assert.assertEquals(removeBookResponse.getErrorList().get(0).getErrorMessage(), "ID not consist to Book Title");
        Assert.assertEquals(appContext.getBean(DatabaseInMemory.class).getListBooks().size(), bookNumberBeforeTest);
        System.out.println();
    }

}
