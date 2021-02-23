package book_library.console_ui.Book;

import book_library.console_ui.UIAction;
import book_library.core.requests.Book.GetAllBooksRequest;
import book_library.core.responses.Book.GetAllBooksResponse;
import book_library.core.services.Book.GetAllBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllBooksUIAction implements UIAction {

    @Autowired
    GetAllBooksService getAllBooksService;

    @Override
    public void execute() {
        System.out.println("Book list:");
        System.out.println("***************************************************************************");
        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService.execute(request);
        response.getBooks().forEach(System.out::println);
        System.out.println("***************************************************************************");
        System.out.println("Book list end.");
    }
}
