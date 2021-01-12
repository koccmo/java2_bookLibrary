package lv.estore.app.core.responses;

import lv.estore.app.core.errors.CoreError;

import java.util.ArrayList;
import java.util.List;

public abstract class CoreResponse {
    private List<CoreError> errors;

    public CoreResponse() { }

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        if (errors.isEmpty()) {
            return new ArrayList<>();
        }
        return errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
