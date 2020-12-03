package core.responses;

public abstract class CoreResponse {
    private Errors errors;

    public CoreResponse() { }

    public CoreResponse(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.getAllErrors().isEmpty();
    }
}
