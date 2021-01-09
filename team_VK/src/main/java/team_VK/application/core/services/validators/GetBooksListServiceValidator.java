package team_VK.application.core.services.validators;

import org.springframework.stereotype.Component;
import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
@Component
public class GetBooksListServiceValidator {

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(GetBookListRequest request){
        return errors;
    }

}
