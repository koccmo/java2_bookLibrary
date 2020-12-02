package team_VK.application.core.services;

import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.GetBookListResponse;
import team_VK.application.database.Database;

import java.util.List;

public class GetBooksListService {

    private final Database database;
    private final GetBooksListServiceValidator validator;


    public GetBooksListService(Database database, GetBooksListServiceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public GetBookListResponse getBooksList(GetBookListRequest request) {

        List<CoreError> errors = validator.validate(request);


        if (errors.size() == 0) {
            System.out.println("Book list:");
            for (Book book : database.getListBooks()) {
                System.out.println(book);
            }
            System.out.println("End of list");
        }
        return new GetBookListResponse(errors);
    }
}