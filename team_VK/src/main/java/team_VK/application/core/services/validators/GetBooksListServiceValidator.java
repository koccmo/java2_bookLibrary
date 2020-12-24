package team_VK.application.core.services.validators;

import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
@DIComponent
public class GetBooksListServiceValidator {

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(GetBookListRequest request){
        return errors;
    }

}
