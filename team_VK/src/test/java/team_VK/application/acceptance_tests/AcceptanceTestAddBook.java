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
import team_VK.application.database.BookRepository;
import team_VK.application.database.DatabaseCleaner;

public class AcceptanceTestAddBook {

//    @Autowired
//    private BookRepository database;
//    @Autowired
//    private AddBookService addBookService;
//    @Autowired
//    private DatabaseCleaner databaseCleaner;

    ApplicationContext context;
    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(LibraryConfig.class);
        DatabaseCleaner databaseCleaner = context.getBean(DatabaseCleaner.class);
        databaseCleaner.clean();
    }



    @Test
    public void shouldAddCorrectBook() {
        BookRepository database = context.getBean(BookRepository.class);
        AddBookService addBookService = context.getBean(AddBookService.class);
        int bookNumberBeforeTest = database.getListBooks().size();

        AddBookRequest addBookRequest = new AddBookRequest("Title1", "Author One", 1);
        AddBookResponse addBookResponse = addBookService.addBook(addBookRequest);

        Assert.assertFalse(addBookResponse.havesError());
        Assert.assertEquals(addBookResponse.errorList.size(), 0);
        Assert.assertEquals(database.getListBooks().size(), bookNumberBeforeTest + 1);
        System.out.println();
    }

    @Test
    public void shouldNotAddInCorrectBook1() {

        BookRepository database = context.getBean(BookRepository.class);
        AddBookService addBookService = context.getBean(AddBookService.class);
        int bookNumberBeforeTest = database.getListBooks().size();

        AddBookRequest addBookRequest = new AddBookRequest("Title1", "Author 1", 1);
        AddBookResponse addBookResponse = addBookService.addBook(addBookRequest);

        Assert.assertTrue(addBookResponse.havesError());
        Assert.assertEquals(addBookResponse.errorList.get(0).getErrorMessage(), "Field bookAuthor contains illegal characters");
        Assert.assertEquals(addBookResponse.errorList.size(), 1);
        Assert.assertEquals(database.getListBooks().size(), bookNumberBeforeTest);
        System.out.println();
    }

    @Test
    public void shouldNotAddInCorrectBook2() {

        BookRepository database = context.getBean(BookRepository.class);
        AddBookService addBookService = context.getBean(AddBookService.class);
        int bookNumberBeforeTest = database.getListBooks().size();

        AddBookRequest addBookRequest = new AddBookRequest("Title@", "Author One", 1);
        AddBookResponse addBookResponse = addBookService.addBook(addBookRequest);

        Assert.assertTrue(addBookResponse.havesError());
        Assert.assertEquals(addBookResponse.errorList.get(0).getErrorMessage(), "Field bookTitle contains illegal characters");
        Assert.assertEquals(addBookResponse.errorList.size(), 1);
        Assert.assertEquals(database.getListBooks().size(), bookNumberBeforeTest);
        System.out.println();
    }
}