package lesson_10.core.responses;

import java.util.Objects;

public class CoreError {
    private final String errorField;
    private final String errorMessage;

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
        return "CoreError {" +
                "Field='" + errorField + '\'' +
                ", Message='" + errorMessage + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreError coreError = (CoreError) o;
        return Objects.equals(errorField, coreError.errorField) &&
                Objects.equals(errorMessage, coreError.errorMessage);
    }
    @Override
    public int hashCode() {
        return Objects.hash(errorField, errorMessage);
    }

}
