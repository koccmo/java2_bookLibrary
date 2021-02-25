package book_library.console_ui.Book;

import book_library.console_ui.UIAction;
import book_library.core.database.Mapper.Book.NewBookIdMapper;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.responses.Book.AddBookResponse;
import book_library.core.services.Book.AddBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddBookUIAction implements UIAction {

    @Autowired
    private AddBookService addBookService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book tittle:");
        String bookTitle = scanner.nextLine();
        System.out.println("Enter book author:");
        String bookAuthor = scanner.nextLine();
        AddBookRequest request = new AddBookRequest(bookTitle, bookAuthor);
        AddBookResponse response = addBookService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(System.out::println);
        } else {
            String sql = "SELECT MAX(id) FROM books";
            System.out.println("New book id is: " + jdbcTemplate.query(sql, new NewBookIdMapper()).get(0));
            System.out.println("Your book was added to list.");
        }

    }
}
