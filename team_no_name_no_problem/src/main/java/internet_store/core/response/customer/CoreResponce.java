package internet_store.core.response.customer;

import java.util.List;

abstract class CoreResponse {

    private List<CoreError> errors;

    public CoreResponse (){ }

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors(){
        return errors;
    }

    public boolean inputErrors(){
        return errors !=null & !errors.isEmpty();
    }
}
