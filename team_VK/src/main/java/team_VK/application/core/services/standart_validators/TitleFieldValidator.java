package team_VK.application.core.services.standart_validators;

import org.springframework.stereotype.Component;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@Component
public class TitleFieldValidator {

    public List<CoreError> validate(String bookTitle) {

        List<CoreError> errors = new ArrayList<>();

        if (bookTitle == null || bookTitle.equals("")) {
            CoreError error = new CoreError("bookTitle", "Field bookTitle must be not empty");
            errors.add(error);
        }

            if (bookTitle.trim().equals("") && !bookTitle.equals("")) {
            CoreError error = new CoreError("bookTitle", "Field bookTitle can't be Space");
            errors.add(error);
        }

        if (bookTitle.trim().length() <= 3 && !bookTitle.trim().equals("") ) {
            CoreError error = new CoreError("bookTitle", "Field bookTitle is too short");
            errors.add(error);
        }

        IntStream streamFromBookTitle = bookTitle.chars();
        boolean bookTitleContainsIllegalCharts = streamFromBookTitle
                .anyMatch(value -> (value <= 31) ||
                        ((value >=34) && (value<=43)) ||
                        ((value >=60) && (value<=64)) ||
                        ((value >= 91) && (value <= 96)) ||
                        ((value >= 123) && (value <= 193))  ||
                        (value >= 256));
        if (bookTitleContainsIllegalCharts) {
            CoreError error = new CoreError("bookTitle", "Field bookTitle contains illegal characters");
            errors.add(error);
        }
    return errors;
    }

}
