package team_VK.application.core.services;

import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class GetBookListServiceValidator {

    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(GetBookListRequest request){
        return errors;
    }

}
