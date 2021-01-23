package lesson_10.ui;

import lesson_10.core.requests.GetAllBooksRequest;
import lesson_10.core.responses.GetAllBooksResponse;
import lesson_10.core.services.GetAllBooksService;
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
