package lv.estore.app.utils;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    private static final String PRICE_REGEX = "[0-9]+([,.][0-9]{1,2})?";

    public boolean isEmptyField (final String value) {
         return value == null || value.length() == 0;
    }

    public boolean isValidPrice(final String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return value.matches(PRICE_REGEX);
    }

    public boolean isValidId(final String value) {
        boolean isValid = false;
        if (value != null && value.length() > 0) {
            try {
                isValid = Long.parseLong(value) > 0;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return isValid;
    }

    public boolean isValidPagingParameters(final Integer value) {
        if (value != null) {
            return value > 0;
        }
        return false;
    }

    public boolean isValidOrderBy(final String value) {
        boolean isValid = false;
        if (!isEmptyField(value)) {
            isValid = "name".equalsIgnoreCase(value) || "price".equalsIgnoreCase(value);
        }
        return isValid;
    }

    public boolean isValidDirection(final String value) {
        boolean isValid = false;
        if (!isEmptyField(value)) {
            isValid = "DESCENDING".equalsIgnoreCase(value) || "ASCENDING".equalsIgnoreCase(value);
        }
        return isValid;
    }

    public boolean isContainingNumbers(final String value) {
       char[] chars = value.toCharArray();
       for (char ch : chars) {
           if (Character.isDigit(ch)) {
               return true;
           }
       }
        return false;
    }
}
