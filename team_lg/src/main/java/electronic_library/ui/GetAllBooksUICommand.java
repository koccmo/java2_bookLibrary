package electronic_library.ui;

import electronic_library.core.requests.GetAllBooksRequest;
import electronic_library.core.responses.GetAllBooksResponse;
import electronic_library.core.services.GetAllBooksService;
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
