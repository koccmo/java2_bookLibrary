package team_VK.application.core.services.standart_validators;

import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@DIComponent
public class BookIDFieldValidator {

    public List<CoreError> validate (String bookIdString){

        List<CoreError> errors = new ArrayList<>();

        IntStream clientPersonalCodeLeftSide = bookIdString.chars();
        boolean     IsNumeric = clientPersonalCodeLeftSide
                .anyMatch(value -> (value >= 48) && (value <= 57));
        if(!IsNumeric) { CoreError error = new CoreError("bookID", "Field bookID must be numeric");
            errors.add(error);}
        return errors;
    }

}
