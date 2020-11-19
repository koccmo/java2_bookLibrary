package team_VK.application.core.responses;

import java.util.Objects;

public class CoreError {

    public String field;
    public String errorMessage;

    public CoreError(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreError error = (CoreError) o;
        return Objects.equals(field, error.field) &&
                Objects.equals(errorMessage, error.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, errorMessage);
    }

    @Override
    public String toString() {
        return "CoreError{" +
                "field='" + field + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
