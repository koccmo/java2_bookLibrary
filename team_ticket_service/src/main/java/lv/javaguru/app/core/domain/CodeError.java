package lv.javaguru.app.core.domain;

public class CodeError {
    private String field;
    private String message;

    public CodeError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CodeError{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getField() {
        return field;
    }

    public void setField(String pName) {
        this.field = pName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
