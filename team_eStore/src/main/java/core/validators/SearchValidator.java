package core.validators;

import core.request.CoreRequest;
import core.responses.CoreError;
import core.responses.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchValidator implements iValidator{

    @Override
    public Errors validate(CoreRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateNamePriceForEmpty(request).get());
        errors.add(validatePrice(request.getPrice()).get());
        errors.addAll(validateOrderByAndDirection(request).get());
        return new Errors(errors);
    }

    private Optional<List<CoreError>> validateNamePriceForEmpty(final CoreRequest request){
        final String name = request.getName();
        final String price = request.getPrice();
        List<CoreError> errors = new ArrayList<>();

        if (isEmpty(name) && isEmpty(price)) {
            errors.add(new CoreError("Name", "Field is empty"));
            errors.add(new CoreError("Price", "Field is empty"));
            return Optional.of(errors);
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validatePrice(final String price){
        String regex = "^(0|\\+?[1-9]\\d*)$[.][0-9]{2}";

        return !isEmpty(price) && price.matches(regex)
                ? Optional.empty()
                : Optional.of(new CoreError("Price", "Value should be valid"));
    }

    private Optional<List<CoreError>> validateOrderByAndDirection(final CoreRequest request){
        List<CoreError> errors = new ArrayList<>();
        final String orderBy = request.getOrderBy();
        final String orderDirection = request.getOrderDirection();
        if (!isEmpty(orderBy) && !isEmpty(orderDirection)) {
            return Optional.empty();
        } else if (isEmpty(orderBy) && isEmpty(orderDirection)) {
            return Optional.empty();
        } else {
            errors.add(validateOrderBy(orderBy).get());
            errors.add(validateOrderDirection(orderDirection).get());
            return Optional.of(errors);
        }
    }

    private Optional <CoreError> validateOrderBy(final String orderBy){
        return !isEmpty(orderBy) && ("name".equals(orderBy) || "price".equals(orderBy))
                ? Optional.empty()
                : Optional.of(new CoreError("OrderBy", "Value should be valid: 'name' or 'price'"));
    }

    private Optional <CoreError> validateOrderDirection(final String orderDirection){
        return !isEmpty(orderDirection) && ("DESC".equals(orderDirection) || "ASC".equals(orderDirection))
                ? Optional.empty()
                : Optional.of(new CoreError("OrderBy", "Value should be valid: 'name' or 'price'"));
    }

    private boolean isEmpty(final String value) {
        return value == null || value.length() == 0;
    }
}
