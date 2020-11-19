package team_VK.application.core.responses;

import java.util.List;

public class AddBookResponse extends CoreResponse {

    public AddBookResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public AddBookResponse() {
    }
}
