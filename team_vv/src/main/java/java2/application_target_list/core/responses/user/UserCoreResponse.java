package java2.application_target_list.core.responses.user;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

abstract class UserCoreResponse {

    private List<CoreError> errorList;

    public UserCoreResponse(List<CoreError> errorList) {
        this.errorList = errorList;
    }

    public UserCoreResponse() {
    }

    public List<CoreError> getErrorList() {
        return errorList;
    }

    public boolean hasErrors() {
        return errorList != null && !errorList.isEmpty();
    }
}
