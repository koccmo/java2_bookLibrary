package team_VK.application.ui;

import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.GetBookListResponse;
import team_VK.application.core.services.GetBooksListService;

public class GetBooksListUIAction implements UIActions {

    private final GetBooksListService getBooksListService;

    public GetBooksListUIAction(GetBooksListService getBooksListService) {
        this.getBooksListService = getBooksListService;
    }

    @Override
    public void execute() {

        GetBookListRequest request = new GetBookListRequest();
        GetBookListResponse response = getBooksListService.getBooksList(request);

        if (!response.havesError()) {
            System.out.println("Please see books list above");
        } else {
            response.getErrorList().forEach(coreError -> System.out.println(coreError.toString()));
        }


        System.out.println();

    }
}
