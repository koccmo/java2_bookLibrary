package adventure_time.core.responses;

public class CoreMessage {

    private String field;
    private String message;

    public CoreMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
