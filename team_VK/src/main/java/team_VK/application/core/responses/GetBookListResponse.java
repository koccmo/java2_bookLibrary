package team_VK.application.core.responses;

import team_VK.application.database.database_Admin.DatabaseInMemory;

import java.util.List;

public class GetBookListResponse extends CoreResponse {

    DatabaseInMemory booksList;
    public GetBookListResponse(List<CoreError> errorList, DatabaseInMemory booksList) {
        super(errorList);
        this.booksList = booksList;
    }

    public DatabaseInMemory getBooksList() {
        return booksList;
    }

    public GetBookListResponse() {
    }
}
