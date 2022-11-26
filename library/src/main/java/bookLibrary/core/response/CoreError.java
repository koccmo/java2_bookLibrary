package bookLibrary.core.response;

import java.util.Objects;

public class CoreError {
    private String field;
    private String description;

    public CoreError(String field, String description) {
        this.field = field;
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreError coreError = (CoreError) o;
        return Objects.equals(field, coreError.field) && Objects.equals(description, coreError.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, description);
    }

    @Override
    public String toString() {
        return "CoreError{" +
                "field='" + field + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
