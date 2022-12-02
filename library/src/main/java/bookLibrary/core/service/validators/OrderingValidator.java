package bookLibrary.core.service.validators;

import bookLibrary.core.request.Ordering;
import bookLibrary.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class OrderingValidator {

    public List<CoreError> validate(Ordering order) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderBy(order).ifPresent(errors::add);
        validationOrderDirection(order).ifPresent(errors::add);
        validationRequiredOrderBy(order).ifPresent(errors::add);
        validationRequiredOrderDirection(order).ifPresent(errors::add);
        return errors;
    }
    private Optional<CoreError> validateOrderBy(Ordering order) {
        return (order.getOrderBy() != null
                && !(order.getOrderBy().equals("Author") || order.getOrderBy().equals("Title")))
                ? Optional.of(new CoreError("OrderBy", "Must be equal Author or Title!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationOrderDirection(Ordering order) {
        return (order.getOrderDirection() != null
                && !(order.getOrderDirection().equals("ASCENDING") || order.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("OrderDirection", "Must be choosing one of Direction!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationRequiredOrderBy(Ordering order) {
        return (order.getOrderDirection() != null && order.getOrderBy() == null)
                ? Optional.of(new CoreError("OrderBy", "Must be fill up!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationRequiredOrderDirection(Ordering order) {
        return (order.getOrderBy() != null && order.getOrderDirection() == null)
                ? Optional.of(new CoreError("OrderDirection", "Must be fill up!"))
                : Optional.empty();
    }
}
