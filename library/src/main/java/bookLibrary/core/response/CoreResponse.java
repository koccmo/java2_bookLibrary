package bookLibrary.core.response;

import java.util.List;

abstract class CoreResponse {
    private List<CoreError> errors;

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public CoreResponse() {
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasError() {
        return errors != null && !errors.isEmpty();
    }
}
