package core.responses.guides;

import core.responses.CoreError;

import java.util.List;

public abstract class CoreResponseGuide {

    private List<CoreError> errors;

    public CoreResponseGuide() { }

    public CoreResponseGuide(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasError() {

        return errors != null && !errors.isEmpty();
    }


}
