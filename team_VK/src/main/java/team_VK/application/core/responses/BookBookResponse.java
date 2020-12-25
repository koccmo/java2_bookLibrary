package team_VK.application.core.responses;

import java.util.List;

public class BookBookResponse extends CoreResponse {

    public BookBookResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public boolean havesError (){

        return errorList.size()>0 ? true : false;
    }

}
