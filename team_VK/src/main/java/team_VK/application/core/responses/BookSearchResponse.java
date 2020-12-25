package team_VK.application.core.responses;

import java.util.List;

public class BookSearchResponse extends CoreResponse {

    public BookSearchResponse(List<CoreError> errorList){ super(errorList);}
    public boolean havesError (){

        return errorList.size()>0 ? true : false;
    }
}
