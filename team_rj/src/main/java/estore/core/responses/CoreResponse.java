package estore.core.responses;

import estore.core.validation.CoreError;

import java.util.List;

public abstract class CoreResponse {

    private List<CoreError> errors;

    public CoreResponse() {
    }

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        if (errors.size() > 0) {
            return true;
        }
        return false;
    }
}
