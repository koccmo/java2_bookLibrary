package team_VK.application.core.services;

import team_VK.application.core.requests.SearchBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.SearchBookResponse;
import team_VK.application.database.DIComponent;
import team_VK.application.database.Database;

import java.util.List;

@DIComponent
public class SearchBookService {
    @DIDependency private Database database;
    @DIDependency public SearchBookServiceValidator validator;


    public SearchBookResponse searchBook(SearchBookRequest request) {

        List<CoreError> errors = validator.validate(request);

        if(errors.size() == 0){
            // book(s) search and output
        }

        return new SearchBookResponse(errors);
    }


}
