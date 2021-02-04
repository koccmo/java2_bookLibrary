package electronic_library.ui.book;

import electronic_library.core.requests.book.GetAllBooksRequest;
import electronic_library.core.responses.book.GetAllBooksResponse;
import electronic_library.core.services.book.GetAllBooksService;
import electronic_library.ui.UICommand;
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
