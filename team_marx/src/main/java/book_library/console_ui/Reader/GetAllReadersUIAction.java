package book_library.console_ui.Reader;

import book_library.console_ui.Book.GetAllBooksUIAction;
import book_library.console_ui.UIAction;
import book_library.core.requests.Book.GetAllBooksRequest;
import book_library.core.responses.Book.GetAllBooksResponse;
import book_library.core.services.Reader.GetAllReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllReadersUIAction implements UIAction {

    @Autowired
    GetAllReadersService getAllReadersService;

    @Override
    public void execute() {
        System.out.println("Reader list:");
        System.out.println("***************************************************************************");
//        GetAllBooksRequest request = new GetAllBooksRequest();
//        GetAllBooksResponse response = getAllBooksService.execute(request);
//        response.getBooks().forEach(System.out::println);
        System.out.println("***************************************************************************");
        System.out.println("Reader list end.");

    }
}
