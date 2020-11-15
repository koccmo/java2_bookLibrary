package team_VK.application.responses;

import java.util.List;

public class AddBookResponse {

    public List<CoreError> addBookErrorList;

    public AddBookResponse() {
    }

    public AddBookResponse(List<CoreError> addBookErrorList) {
        this.addBookErrorList = addBookErrorList;
    }

    public List<CoreError> getAddBookErrorList() {
        return addBookErrorList;
    }

    public boolean havesError() {

        if (addBookErrorList.size()>0) {
            return true;
        } else return false;
    }
}
