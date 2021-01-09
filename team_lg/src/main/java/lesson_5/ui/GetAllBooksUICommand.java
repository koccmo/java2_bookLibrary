package lesson_5.ui;

import lesson_5.core.requests.GetAllBooksRequest;
import lesson_5.core.responses.GetAllBooksResponse;
import lesson_5.core.services.GetAllBooksService;

public class GetAllBooksUICommand implements UICommand {

    private final GetAllBooksService getAllBooksService;

    public GetAllBooksUICommand(GetAllBooksService getAllBooksService) {
        this.getAllBooksService = getAllBooksService;
    }

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
