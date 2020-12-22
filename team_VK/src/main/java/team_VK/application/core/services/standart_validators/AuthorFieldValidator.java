package team_VK.application.core.services.standart_validators;


import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@DIComponent
public class AuthorFieldValidator {

    public List<CoreError> validate(String bookAuthor) {
        List<CoreError> errors = new ArrayList<>();

        if (bookAuthor == null || bookAuthor.equals("")) {
            CoreError error = new CoreError("bookAuthor", "Field bookAuthor must be not empty");
            errors.add(error);
        }

        if (bookAuthor.trim().equals("") && !bookAuthor.equals("")) {
            CoreError error = new CoreError("bookAuthor", "Field bookAuthor can't be Space");
            errors.add(error);
        }

        if (bookAuthor.trim().length() <= 3 && !bookAuthor.trim().equals("")) {
            CoreError error = new CoreError("bookAuthor", "Field bookAuthor is too short");
            errors.add(error);
        }

        IntStream streamFromBookAuthor = bookAuthor.chars();
        boolean bookAuthorContainsIllegalCharts = streamFromBookAuthor
                .anyMatch(value -> (value <= 31) ||
                        ((value >= 33) && (value <= 64)) ||
                        ((value >= 91) && (value <= 96)) ||
                        ((value >= 123) && (value <= 159)) ||
                        ((value >= 174) && (value <= 180)) ||
                        ((value >= 185) && (value <= 207)) ||
                        ((value >= 217) && (value <= 220)) ||
                        (value == 223) ||
                        (value >= 253));
        if (bookAuthorContainsIllegalCharts) {
            CoreError error = new CoreError("bookAuthor", "Field bookAuthor contains illegal characters");
            errors.add(error);
        }
        return errors;
    }

}
