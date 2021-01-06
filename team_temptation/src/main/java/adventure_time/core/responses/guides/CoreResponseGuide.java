package adventure_time.core.responses.guides;

import adventure_time.core.responses.CoreError;

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
