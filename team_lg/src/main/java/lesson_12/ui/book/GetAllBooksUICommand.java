package lesson_12.ui.book;

import lesson_12.core.requests.book.GetAllBooksRequest;
import lesson_12.core.responses.book.GetAllBooksResponse;
import lesson_12.core.services.book.GetAllBooksService;
import lesson_12.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllBooksUICommand implements UICommand {

    @Autowired
    private GetAllBooksService getAllBooksService;

    @Override
    public void execute() {
        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = getAllBooksService.execute(request);
        System.out.println("================================================================================================");
        System.out.println("ELECTRONIC LIBRARY LIST:");
        if (response.getBooks().isEmpty()) {
            System.out.println("Electronic library is empty...");
        } else
            response.getBooks().forEach(System.out::println);
        System.out.println("================================================================================================");
    }
}
