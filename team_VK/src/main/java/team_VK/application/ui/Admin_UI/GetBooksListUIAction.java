package team_VK.application.ui.Admin_UI;

import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.GetBookListResponse;
import team_VK.application.core.services.admin_services.GetBooksListService;

import java.awt.*;

public class GetBooksListUIAction implements UIActions {

    private final GetBooksListService getBooksListService;

    public GetBooksListUIAction(GetBooksListService getBooksListService) {
        this.getBooksListService = getBooksListService;
    }

    @Override
    public void execute() {

        GetBookListRequest request = new GetBookListRequest();
        GetBookListResponse response = getBooksListService.getBooksList(request);


        System.out.println("Book list:");

        for (Book book : response.getBooksList().getListBooks()) {
            System.out.println(book);
        }
        System.out.println("End of list");
        System.out.println();

    }
}
