package application_target_list.core.responses;


import java.util.List;

abstract class CoreResponse {

    private List<CoreError> errorList;

    public CoreResponse(List<CoreError> errorList) {
        this.errorList = errorList;
    }

    public CoreResponse() {
    }

    public List<CoreError> getErrorList() {
        return errorList;
    }

    public boolean hasErrors() {
        return errorList != null && !errorList.isEmpty();
    }


}
