package java2.application_target_list.core.responses.board;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

abstract class RecordCoreResponse {

    private List<CoreError> errorList;

    public RecordCoreResponse(List<CoreError> errorList) {
        this.errorList = errorList;
    }

    public RecordCoreResponse() {
    }

    public List<CoreError> getErrorList() {
        return errorList;
    }

    public boolean hasErrors() {
        return errorList != null && !errorList.isEmpty();
    }
}
