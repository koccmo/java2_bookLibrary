package internet_store.core.responses;

import java.util.List;

abstract class CoreResponse {

    List<CoreError> error;

    public CoreResponse(List<CoreError> error) { this.error = error; }

    protected CoreResponse() {
    }

    public List<CoreError> getError() { return error; }

    public boolean hasErrors() { return error != null && !error.isEmpty(); }
}
