package team_VK.application.acceptance_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import team_VK.application.configuration.LibraryConfig;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.AddBookResponse;
import team_VK.application.core.services.main_menu_services.AddBookService;
import team_VK.application.database.Database;
import team_VK.application.database.DatabaseCleaner;

public class AcceptanceTestAddBook {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(LibraryConfig.class);

//        DataBaseFillAdditionalFunction dataBaseFillAdditionalFunction =
//                appContext.getBean(DataBaseFillAdditionalFunction.class);
//
//        dataBaseFillAdditionalFunction.execute();

        appContext = new AnnotationConfigApplicationContext(LibraryConfig.class);
        getDatabaseCleaner().clean();


    }


    @Test
    public void shouldAddCorrectBook(){

        int bookNumberBeforeTest = appContext.getBean(Database.class).getListBooks().size();

        AddBookService addBookService = appContext.getBean(AddBookService.class);
        AddBookRequest addBookRequest = new AddBookRequest("Title1", "Author One", 1);
        AddBookResponse addBookResponse = addBookService.addBook(addBookRequest);

        Assert.assertFalse(addBookResponse.havesError());
        Assert.assertEquals(addBookResponse.errorList.size(), 0);
        Assert.assertEquals(appContext.getBean(Database.class).getListBooks().size(), bookNumberBeforeTest+1);
        System.out.println();


    }

    @Test
    public void shouldNotAddInCorrectBook1(){

        int bookNumberBeforeTest = appContext.getBean(Database.class).getListBooks().size();

        AddBookService addBookService = appContext.getBean(AddBookService.class);
        AddBookRequest addBookRequest = new AddBookRequest("Title1", "Author 1", 1);
        AddBookResponse addBookResponse = addBookService.addBook(addBookRequest);

        Assert.assertTrue(addBookResponse.havesError());
        Assert.assertEquals(addBookResponse.errorList.get(0).getErrorMessage(), "Field bookAuthor contains illegal characters");
        Assert.assertEquals(addBookResponse.errorList.size(), 1);
        Assert.assertEquals(appContext.getBean(Database.class).getListBooks().size(), bookNumberBeforeTest);
        System.out.println();


    }

    @Test
    public void shouldNotAddInCorrectBook2(){

        int bookNumberBeforeTest = appContext.getBean(Database.class).getListBooks().size();

        AddBookService addBookService = appContext.getBean(AddBookService.class);
        AddBookRequest addBookRequest = new AddBookRequest("Title@", "Author One", 1);
        AddBookResponse addBookResponse = addBookService.addBook(addBookRequest);

        Assert.assertTrue(addBookResponse.havesError());
        Assert.assertEquals(addBookResponse.errorList.get(0).getErrorMessage(), "Field bookTitle contains illegal characters");
        Assert.assertEquals(addBookResponse.errorList.size(), 1);
        Assert.assertEquals(appContext.getBean(Database.class).getListBooks().size(), bookNumberBeforeTest);
        System.out.println();


    }
    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

}
