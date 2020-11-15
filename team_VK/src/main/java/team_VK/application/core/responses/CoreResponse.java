package team_VK.application.core.responses;

import java.util.List;

public abstract class CoreResponse {

    public List<CoreError> errorList;

    public CoreResponse(List<CoreError> errorList) {
        this.errorList = errorList;
    }

    public CoreResponse() {}

    public List<CoreError> getErrorList() {
        return errorList;
    }
    public boolean havesError() {

        if (errorList.size()>0) {
            return true;
        } else return false;
    }


}
