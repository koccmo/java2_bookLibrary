package team_VK.application.core.services;

import team_VK.application.core.requests.BookSearchRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;

import java.util.List;
@DIComponent
public class BookSearchServiceValidator {

   public List<CoreError> validate(BookSearchRequest request){

        return null;
    }

}
