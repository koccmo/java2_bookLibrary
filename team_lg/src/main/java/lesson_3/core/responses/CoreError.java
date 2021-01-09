package lesson_3.core.responses;

public class CoreError {
    private String errorField;
    private String errorMessage;

    public CoreError(String errorField, String errorMessage) {
        this.errorField = errorField;
        this.errorMessage = errorMessage;
    }

    public String getErrorField() {
        return errorField;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "CoreError{" +
                "errorField='" + errorField + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
