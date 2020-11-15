package team_VK.application.core.services.admin_services;

import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.GetBookListResponse;
import team_VK.application.database.database_Admin.Database;

import java.util.List;

public class GetBooksListService {

    private final Database database;
    private  final GetBookListServiceValidator validator;

    public GetBooksListService(Database database, GetBookListServiceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public GetBookListResponse getBooksList (GetBookListRequest request)
    {
        GetBookListResponse response = new GetBookListResponse ();

        return new GetBookListResponse(validator.validate(request), response.getBooksList());
    }
}
