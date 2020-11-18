package team_VK.application.core.services.admin_services;

import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class RemoveBookServiceValidator {


    public List<CoreError> validate(RemoveBookRequest request){
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
