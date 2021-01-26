package adventure_time.core.responses;

import java.util.List;

public abstract class CoreResponse {

    private List<CoreError> errors;
    private Object object;

    public CoreResponse() { }

    public CoreResponse (Object object) { this.object = object;}

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public Object getObject() {return object;}

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasError() {

        return errors != null && !errors.isEmpty();
    }
}
