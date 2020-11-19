package team_VK.application.core.responses;

import team_VK.application.database.database_Admin.DatabaseInMemory;

import java.util.List;

public class GetBookListResponse extends CoreResponse {

    public GetBookListResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public GetBookListResponse() {
    }
}
